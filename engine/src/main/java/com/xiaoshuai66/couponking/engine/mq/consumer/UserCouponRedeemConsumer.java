package com.xiaoshuai66.couponking.engine.mq.consumer;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.xiaoshuai66.couponking.engine.common.constant.EngineRedisConstant;
import com.xiaoshuai66.couponking.engine.dao.entity.UserCouponDO;
import com.xiaoshuai66.couponking.engine.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.engine.dao.mapper.UserCouponMapper;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRedeemReqDTO;
import com.xiaoshuai66.couponking.engine.dto.resp.CouponTemplateQueryRespDTO;
import com.xiaoshuai66.couponking.engine.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.engine.mq.event.UserCouponDelayCloseEvent;
import com.xiaoshuai66.couponking.engine.mq.event.UserCouponRedeemEvent;
import com.xiaoshuai66.couponking.engine.mq.producer.UserCouponDelayCloseProducer;
import com.xiaoshuai66.couponking.framework.exception.ServiceException;
import com.xiaoshuai66.couponking.framework.idempotent.NoMQDuplicateConsume;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * ClassName: UserCouponRedeemConsumer
 * Package: com.xiaoshuai66.couponking.engine.mq.consumer
 * Description: 用户兑换优惠券消息消费者
 *
 * @Author 赵帅
 * @Create 2025/2/26 22:07
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = "coupon-king_engine-service_user-coupon-redeem_topic",
        consumerGroup = "coupon-king_engine-service_user-coupon-redeem_cg"
)
@Slf4j(topic = "UserCouponRedeemConsumer")
public class UserCouponRedeemConsumer implements RocketMQListener<MessageWrapper<UserCouponRedeemEvent>> {

    private final CouponTemplateMapper couponTemplateMapper;
    private final UserCouponMapper userCouponMapper;
    private final UserCouponDelayCloseProducer couponDelayCloseProducer;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${coupon-king.user-coupon-list.save-cache.type:direct}")
    private String userCouponListSaveCacheType;

    @NoMQDuplicateConsume(
            keyPrefix = "user-coupon-redeem:",
            key = "#messageWrapper.keys",
            keyTimeout = 600
    )
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void onMessage(MessageWrapper<UserCouponRedeemEvent> messageWrapper) {
        // 开头打印日志，平常可 Debug 看任务参数，线上可报平安（比如消息是否消费，重新投递时获取参数等）
        log.info("[消费者] 用户兑换优惠券 - 执行消费逻辑，消息体：{}", JSON.toJSONString(messageWrapper));

        CouponTemplateRedeemReqDTO requestParam = messageWrapper.getMessage().getRequestParam();
        CouponTemplateQueryRespDTO couponTemplate = messageWrapper.getMessage().getCouponTemplate();
        String userId = messageWrapper.getMessage().getUserId();

        int decrement = couponTemplateMapper.decrementCouponTemplateStock(Long.parseLong(requestParam.getShopNumber()), Long.parseLong(requestParam.getCouponTemplateId()), 1L);
        if (!SqlHelper.retBool(decrement)) {
            throw new ServiceException("优惠券已被领取完啦");
        }

        // 添加 Redis 用户领取的优惠券记录列表
        Date now = new Date();
        DateTime validEndTime = DateUtil.offsetHour(now, JSON.parseObject(couponTemplate.getConsumeRule()).getInteger("validityPeriod"));
        UserCouponDO userCouponDO = UserCouponDO.builder()
                .couponTemplateId(Long.parseLong(requestParam.getCouponTemplateId()))
                .userId(Long.parseLong(userId))
                .source(requestParam.getSource())
                .receiveCount(messageWrapper.getMessage().getReceiveCount())
                .status(0)
                .receiveTime(now)
                .validStartTime(now)
                .validEndTime(validEndTime)
                .build();
        userCouponMapper.insert(userCouponDO);

        // 保存优惠券缓存集合有两个选项：direct 在流程里直接操作，binlog 通过解析数据库日志后操作
        if (StrUtil.equals(userCouponListSaveCacheType, "direct")) {
            // 添加用户领取优惠券模板缓存记录
            String userCouponListCacheKey = String.format(EngineRedisConstant.USER_COUPON_TEMPLATE_LIST_KEY, userId);
            String userCouponItemCacheKey = StrUtil.builder()
                    .append(requestParam.getCouponTemplateId())
                    .append("_")
                    .append(userCouponDO.getId())
                    .toString();
            stringRedisTemplate.opsForZSet().add(userCouponListCacheKey, userCouponItemCacheKey, now.getTime());

            // 由于 Redis 在持久化或主从复制的极端情况下可能会出现数据丢失，而我们对指令丢失几乎无法容忍，因此我们采用经典的写后查询策略来应对这一问题
            Double scored;
            try {
                scored = stringRedisTemplate.opsForZSet().score(userCouponListCacheKey, userCouponItemCacheKey);
                // scored 为空意味着可能 Redis Cluster 主从同步丢失了数据，比如 Redis 主节点还没有同步到从节点就宕机了，解决方案就是再新增一次
                if (scored == null) {
                    // 如果这里也新增失败了怎么办？我们大概率做不到绝对的万无一失，只能尽可能增加成功率
                    stringRedisTemplate.opsForZSet().add(userCouponListCacheKey, userCouponItemCacheKey, now.getTime());
                }
            } catch (Throwable ex) {
                log.warn("查询Redis用户优惠券记录为空或抛异常，可能Redis宕机或主从复制数据丢失，基础错误信息：{}", ex.getMessage());
                // 如果直接抛异常大概率 Redis 宕机了，所以应该写个延时队列向 Redis 重试放入值。为了避免代码复杂性，这里直接写新增，大家知道最优解决方案即可
                stringRedisTemplate.opsForZSet().add(userCouponListCacheKey, userCouponItemCacheKey, now.getTime());
            }

            // 发送延时消息队列，等待优惠券到期后，将优惠券信息从缓存中删除
            UserCouponDelayCloseEvent userCouponDelayCloseEvent = UserCouponDelayCloseEvent.builder()
                    .couponTemplateId(requestParam.getCouponTemplateId())
                    .userCouponId(String.valueOf(userCouponDO.getId()))
                    .userId(userId)
                    .delayTime(validEndTime.getTime())
                    .build();
            SendResult sendResult = couponDelayCloseProducer.sendMessage(userCouponDelayCloseEvent);

            // 发送消息失败解决方案简单且高效的逻辑之一：打印日志并报警，通过日志搜集并重新投递
            if (ObjectUtil.notEqual(sendResult.getSendStatus().name(), "SEND_OK")) {
                log.warn("[消费者] 用户兑换优惠券 - 执行消费逻辑，发送优惠券关闭延时队列失败，消息参数：{}", JSON.toJSONString(userCouponDelayCloseEvent));
            }
        }
    }
}
