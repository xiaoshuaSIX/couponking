package com.xiaoshuai66.couponking.merchant.admin.mq.producer;

import cn.hutool.core.util.StrUtil;
import com.xiaoshuai66.couponking.merchant.admin.mq.base.BaseSendExtendDTO;
import com.xiaoshuai66.couponking.merchant.admin.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.merchant.admin.mq.event.CouponTemplateDelayEvent;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * ClassName: CouponTemplateDelayEvent
 * Package: com.xiaoshuai66.couponking.merchant.admin.mq.producer
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/16 20:39
 * @Version 1.0
 */
@Component
public class CouponTemplateDelayExecuteStatusProducer extends AbstractCommonSendProduceTemplate<CouponTemplateDelayEvent>{

    public CouponTemplateDelayExecuteStatusProducer(RocketMQTemplate rocketMQTemplate) {
        super(rocketMQTemplate);
    }

    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(CouponTemplateDelayEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("优惠券模版关闭定时执行")
                .keys(String.valueOf(messageSendEvent.getCouponTemplateId()))
                .topic("coupon-king_merchant-admin-service_coupon-template-delay_topic")
                .delayTime(messageSendEvent.getDelayTime())
                .build();
    }

    @Override
    protected Message<?> buildMessage(CouponTemplateDelayEvent messageSendEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(keys, messageSendEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
