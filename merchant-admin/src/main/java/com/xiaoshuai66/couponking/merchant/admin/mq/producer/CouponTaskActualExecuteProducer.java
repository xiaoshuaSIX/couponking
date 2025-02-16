package com.xiaoshuai66.couponking.merchant.admin.mq.producer;

import cn.hutool.core.util.StrUtil;
import com.xiaoshuai66.couponking.merchant.admin.mq.base.BaseSendExtendDTO;
import com.xiaoshuai66.couponking.merchant.admin.mq.base.MessageWrapper;
import com.xiaoshuai66.couponking.merchant.admin.mq.event.CouponTaskExecuteEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * ClassName: CouponTaskActualExecuteProducer
 * Package: com.xiaoshuai66.couponking.merchant.admin.mq.producer
 * Description: 优惠券推送任务执行生产者
 *
 * @Author 赵帅
 * @Create 2025/2/16 19:41
 * @Version 1.0
 */
@Slf4j
@Component
public class CouponTaskActualExecuteProducer extends AbstractCommonSendProduceTemplate<CouponTaskExecuteEvent>{

    /**
     * 将 rocketMQTemplate 传递给抽象层
     */
    public CouponTaskActualExecuteProducer(RocketMQTemplate rocketMQTemplate) {
        super(rocketMQTemplate);
    }

    @Override
    protected BaseSendExtendDTO buildBaseSendExtendParam(CouponTaskExecuteEvent messageSendEvent) {
        return BaseSendExtendDTO.builder()
                .eventName("优惠券推送任务")
                .keys(String.valueOf(messageSendEvent.getCouponTaskId()))
                .topic("coupon-king_distribution-service_coupon-task-execute_topic")
                .sendTimeout(2000L)
                .build();
    }

    @Override
    protected Message<?> buildMessage(CouponTaskExecuteEvent messageSendEvent, BaseSendExtendDTO requestParam) {
        String keys = StrUtil.isEmpty(requestParam.getKeys()) ? UUID.randomUUID().toString() : requestParam.getKeys();
        return MessageBuilder
                .withPayload(new MessageWrapper(keys, messageSendEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, requestParam.getTag())
                .build();
    }
}
