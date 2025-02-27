package com.xiaoshuai66.couponking.engine.mq.producer;

import cn.hutool.core.util.StrUtil;
import com.xiaoshuai66.couponking.engine.mq.base.BaseSendExtendDTO;
import com.xiaoshuai66.couponking.engine.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.engine.mq.event.CouponTemplateRemindDelayEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @className: CouponTemplateRemindDelayProducer
 * @Description: 提醒抢券生产者
 * @author: zhaoshuai
 * @date: 2025/2/27 20:37
 */
@Slf4j
@Component
public class CouponTemplateRemindDelayProducer extends AbstractCommonSendProducerTemplate<CouponTemplateRemindDelayEvent>{


    public CouponTemplateRemindDelayProducer(RocketMQTemplate rocketMQTemplate) {
        super(rocketMQTemplate);
    }

    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(CouponTemplateRemindDelayEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("提醒用户抢券")
                .keys(messageSendEvent.getUserId() + ":" + messageSendEvent.getCouponTemplateId())
                .topic("coupon-king_engine-service_coupon-remind_topic")
                .sentTimeout(2000L)
                .delayTime(messageSendEvent.getDelayTime())
                .build();
    }

    @Override
    protected Message<?> buildMessage(CouponTemplateRemindDelayEvent messageSendEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(keys, messageSendEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
