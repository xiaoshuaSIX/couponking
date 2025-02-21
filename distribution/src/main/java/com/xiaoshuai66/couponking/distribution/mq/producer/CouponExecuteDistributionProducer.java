package com.xiaoshuai66.couponking.distribution.mq.producer;

import cn.hutool.core.util.StrUtil;
import com.xiaoshuai66.couponking.distribution.mq.base.BaseSendExtendDTO;
import com.xiaoshuai66.couponking.distribution.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.distribution.mq.event.CouponTemplateDistributionEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName: CouponExecuteDistributionProducer.java
 * @Description: 优惠券推送任务执行生产者
 * @author 赵帅
 * @Create 2025/2/20 17:14
 */
@Slf4j
@Component
public class CouponExecuteDistributionProducer extends AbstractCommonSendProduceTemplate<CouponTemplateDistributionEvent>{

    public CouponExecuteDistributionProducer(RocketMQTemplate rocketMQTemplate) {
        super(rocketMQTemplate);
    }

    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(CouponTemplateDistributionEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("优惠券发放执行")
                .keys(String.valueOf(messageSendEvent.getCouponTaskId()))
                .topic("one-coupon_distribution-service_coupon-execute-distribution_topic")
                .sentTimeout(2000L)
                .build();
    }

    @Override
    protected Message<?> buildMessage(CouponTemplateDistributionEvent event, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(keys, event))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
