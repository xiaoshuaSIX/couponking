package com.xiaoshuai66.couponking.engine.mq.producer;

import cn.hutool.core.util.StrUtil;
import com.xiaoshuai66.couponking.engine.mq.base.BaseSendExtendDTO;
import com.xiaoshuai66.couponking.engine.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.engine.mq.event.UserCouponDelayCloseEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author 赵帅
 * @Description: 用户优惠券延迟关闭生产者
 * @Create 2025/2/25 22:07
 */
@Slf4j
@Component
public class UserCouponDelayCloseProducer extends AbstractCommonSendProducerTemplate<UserCouponDelayCloseEvent> {

    public UserCouponDelayCloseProducer(RocketMQTemplate rocketMQTemplate) {
        super(rocketMQTemplate);
    }

    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(UserCouponDelayCloseEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("延迟关闭用户已领取优惠券")
                .keys(String.valueOf(messageSendEvent.getUserCouponId()))
                .topic("coupon-king_engine-service_user-coupon-delay-close_topic")
                .sentTimeout(2000L)
                .delayTime(messageSendEvent.getDelayTime())
                .build();
    }

    @Override
    protected Message<?> buildMessage(UserCouponDelayCloseEvent messageSendEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(requestParam.getKeys(), messageSendEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
