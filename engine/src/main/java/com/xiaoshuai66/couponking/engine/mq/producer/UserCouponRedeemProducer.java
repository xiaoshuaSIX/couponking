package com.xiaoshuai66.couponking.engine.mq.producer;

import cn.hutool.core.util.StrUtil;
import com.xiaoshuai66.couponking.engine.mq.base.BaseSendExtendDTO;
import com.xiaoshuai66.couponking.engine.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.engine.mq.event.UserCouponRedeemEvent;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * ClassName: UserCouponRedeemProducer
 * Package: com.xiaoshuai66.couponking.engine.mq.producer
 * Description: 用户兑换优惠券消息生产者
 *
 * @Author 赵帅
 * @Create 2025/2/26 21:46
 * @Version 1.0
 */
@Component
public class UserCouponRedeemProducer extends AbstractCommonSendProducerTemplate<UserCouponRedeemEvent>{

    public UserCouponRedeemProducer(RocketMQTemplate rocketMQTemplate) {
        super(rocketMQTemplate);
    }

    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(UserCouponRedeemEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("用户兑换优惠券")
                .keys(UUID.randomUUID().toString())
                .topic("coupon-king_engine-service_user-coupon-redeem_topic")
                .sentTimeout(2000L)
                .build();
    }

    @Override
    protected Message<?> buildMessage(UserCouponRedeemEvent messageSendEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(requestParam.getKeys(), messageSendEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
