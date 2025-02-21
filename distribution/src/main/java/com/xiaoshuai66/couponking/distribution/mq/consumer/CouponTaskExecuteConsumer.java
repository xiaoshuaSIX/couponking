package com.xiaoshuai66.couponking.distribution.mq.consumer;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoshuai66.couponking.distribution.common.enums.CouponTaskStatusEnum;
import com.xiaoshuai66.couponking.distribution.common.enums.CouponTemplateStatusEnum;
import com.xiaoshuai66.couponking.distribution.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.distribution.dao.mapper.CouponTaskFailMapper;
import com.xiaoshuai66.couponking.distribution.dao.mapper.CouponTaskMapper;
import com.xiaoshuai66.couponking.distribution.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.distribution.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.distribution.mq.event.CouponTaskExecuteEvent;
import com.xiaoshuai66.couponking.distribution.mq.producer.CouponExecuteDistributionProducer;
import com.xiaoshuai66.couponking.distribution.service.handler.excel.CouponTaskExcelObject;
import com.xiaoshuai66.couponking.distribution.service.handler.excel.ReadExcelDistributionListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 赵帅
 * @ClassName: CouponTaskExecuteConsumer.java
 * @Description: 优惠券推送定时执行-真实执行消费者
 * @Create 2025/2/18 11:15
 */
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = "coupon-king_distribution-service_coupon-task-execute_topic",
        consumerGroup = "coupon-king_distribution-service_coupon-task-execute_cg"
)
@Slf4j(topic = "CouponTaskExecuteConsumer")
public class CouponTaskExecuteConsumer implements RocketMQListener<MessageWrapper<CouponTaskExecuteEvent>> {

    private final CouponTaskMapper couponTaskMapper;
    private final CouponTemplateMapper couponTemplateMapper;
    private final CouponTaskFailMapper couponTaskFailMapper;

    private final StringRedisTemplate stringRedisTemplate;
    private final CouponExecuteDistributionProducer couponExecuteDistributionProducer;

    @Override
    public void onMessage(MessageWrapper<CouponTaskExecuteEvent> messageWrapper) {
        // 开头打印日志
        log.info("[消费者] 优惠券推送任务正式执行 - 执行消费逻辑，消息体：{}", JSON.toJSONString(messageWrapper));

        // 判断优惠券模板发送状态是否为执行中
        var couponTaskId = messageWrapper.getMessage().getCouponTaskId();
        var couponTaskDO = couponTaskMapper.selectById(couponTaskId);
        if (ObjectUtil.notEqual(couponTaskDO.getStatus(), CouponTaskStatusEnum.IN_PROGRESS.getStatus())) {
            log.warn("[消费者] 优惠券推送任务正式执行 - 推送任务状态异常：{}，已终止", couponTaskDO.getStatus());
            return;
        }

        // 判断优惠券状态是否正确
        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getId, couponTaskDO.getCouponTemplateId())
                .eq(CouponTemplateDO::getShopNumber, couponTaskDO.getShopNumber());

        var couponTemplateDO = couponTemplateMapper.selectOne(queryWrapper);
        var status =  couponTemplateDO.getStatus();
        if (ObjectUtil.notEqual(status, CouponTemplateStatusEnum.ACTIVE.getStatus())) {
            log.error("[消费者] 优惠券推送任务正式执行 - 优惠券ID：{}，优惠券模板状态：{}", couponTaskDO.getCouponTemplateId(), status);
            return;
        }

        // 正式开始执行优惠券推送任务
        var readExcelDistributionListener = new ReadExcelDistributionListener(
                couponTaskDO,
                couponTemplateDO,
                couponTaskFailMapper,
                stringRedisTemplate,
                couponExecuteDistributionProducer
        );
        EasyExcel.read(couponTaskDO.getFileAddress(), CouponTaskExcelObject.class, readExcelDistributionListener).sheet().doRead();
    }
}
