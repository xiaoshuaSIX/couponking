package com.xiaoshuai66.couponking.merchant.admin.mq.consumer;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoshuai66.couponking.merchant.admin.common.enums.CouponTemplateStatusEnum;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.merchant.admin.service.CouponTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * ClassName: CouponTemplateDelayExecuteStatusConsumer
 * Package: com.xiaoshuai66.couponking.merchant.admin.mq.consumer
 * Description: 优惠券推送延迟执行-变更记录发送状态消费者
 *
 * @Author 赵帅
 * @Create 2025/2/15 19:00
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = "coupon-king_merchant-admin-service_coupon-template-delay_topic",
        consumerGroup = "coupon-king_merchant-admin-service_coupon-template-delay-status_cg"
)
@Slf4j(topic = "CouponTemplateDelayExecuteStatusConsumer")
public class CouponTemplateDelayExecuteStatusConsumer implements RocketMQListener<JSONObject> {

    private final CouponTemplateService couponTemplateService;

    @Override
    public void onMessage(JSONObject message) {
        // 开头打印日志，平常可 Debug 看任务参数，线上可报平安（比如消息是否消费，重新投递时间获取参数等）
        log.info("[消费者] 优惠券模版定时执行@变更模版状态 - 执行消费逻辑，消息体：{}", message.toString());

        // 修改指定优惠券模版状态为已结束
        LambdaUpdateWrapper<CouponTemplateDO> updateWrapper = Wrappers.lambdaUpdate(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getShopNumber, message.getLong("shopNumber"))
                .eq(CouponTemplateDO::getId, message.getLong("couponTemplateId"))
                .set(CouponTemplateDO::getStatus, CouponTemplateStatusEnum.ENDED.getStatus());
        couponTemplateService.update(updateWrapper);
    }
}
