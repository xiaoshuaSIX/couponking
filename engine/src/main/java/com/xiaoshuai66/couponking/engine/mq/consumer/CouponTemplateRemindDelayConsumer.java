package com.xiaoshuai66.couponking.engine.mq.consumer;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.xiaoshuai66.couponking.engine.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.engine.mq.event.CouponTemplateRemindDelayEvent;
import com.xiaoshuai66.couponking.engine.service.handler.remind.CouponTemplateRemindExecutor;
import com.xiaoshuai66.couponking.engine.service.handler.remind.dto.CouponTemplateRemindDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @className: CouponTemplateRemindDelayConsumer
 * @Description: 提醒抢券消费者
 * @author: zhaoshuai
 * @date: 2025/2/27 20:56
 */
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = "coupon-king_engine-service_coupon-remind_topic",
        consumerGroup = "coupon-king_engine-service_coupon-remind_gc"
)
@Slf4j(topic = "CouponTemplateRemindDelayConsumer")
public class CouponTemplateRemindDelayConsumer implements RocketMQListener<MessageWrapper<CouponTemplateRemindDelayEvent>> {

    private final CouponTemplateRemindExecutor couponTemplateRemindExecutor;

    @Override
    public void onMessage(MessageWrapper<CouponTemplateRemindDelayEvent> messageWrapper) {
        // 开头打印日志，平常可 Debug 看任务参数，线上可报平安（比如消息是否消费，重新投递时获取参数等）
        log.info("[消费者] 提醒用户抢券 - 执行消费逻辑，消息体：{}", JSON.toJSONString(messageWrapper));

        CouponTemplateRemindDelayEvent event = messageWrapper.getMessage();
        CouponTemplateRemindDTO couponTemplateRemindDTO = BeanUtil.toBean(event, CouponTemplateRemindDTO.class);

        // 根据不同策略向用户发送消息提醒
        couponTemplateRemindExecutor.executeRemindCouponTemplate(couponTemplateRemindDTO);
    }
}
