package com.xiaoshuai66.couponking.merchant.admin.mq.producer;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.xiaoshuai66.couponking.merchant.admin.mq.base.BaseSendExtendDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;

/**
 * ClassName: AbstractCommonSendProduceTemplate
 * Package: com.xiaoshuai66.couponking.merchant.admin.mq.producer
 * Description: RocketMQ 抽象公共发送消息组件
 *
 * @Author 赵帅
 * @Create 2025/2/16 15:53
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j(topic = "CommonSendProduceTemplate")
public abstract class AbstractCommonSendProduceTemplate<T> {

    private final RocketMQTemplate rocketMQTemplate;

    /**
     * 构建消息发送事件基础扩充属性实体
     *
     * @param messageSendEvent 消息发送事件
     * @return 扩充属性实体
     */
    protected abstract BaseSendExtendDTO buildBaseSendExtendParam(T messageSendEvent);

    /**
     * 构建消息基本参数，请求头、Keys...
     *
     * @param messageSendEvent  消息发送事件
     * @param requestParam      扩充属性实体
     * @return 消息基本参数
     */
    protected abstract Message<?> buildMessage(T messageSendEvent, BaseSendExtendDTO requestParam);

    /**
     * 消息事件通用发送
     *
     * @param messageSendEvent 消息发送事件
     * @return 消息发送返回结果
     */
    public SendResult sendMessage(T messageSendEvent) {
        BaseSendExtendDTO baseSendExtendDTO = buildBaseSendExtendParam(messageSendEvent);
        SendResult sendResult;
        try {
            // 构建 Topic 目标落点 formats: `topicName:tags`
            StringBuilder destinationBuilder = StrUtil.builder().append(baseSendExtendDTO.getTopic());
            if (StrUtil.isNotBlank(baseSendExtendDTO.getTag())) {
                destinationBuilder.append(":").append(baseSendExtendDTO.getTag());
            }

            // 延迟时间不为空，发送任意延迟消息，否则发送普通消息
            if (baseSendExtendDTO.getDelayTime() != null) {
                sendResult = rocketMQTemplate.syncSendDeliverTimeMills(
                        destinationBuilder.toString(),
                        buildMessage(messageSendEvent, baseSendExtendDTO),
                        baseSendExtendDTO.getDelayTime()
                );
            } else {
                sendResult = rocketMQTemplate.syncSend(
                        destinationBuilder.toString(),
                        buildMessage(messageSendEvent, baseSendExtendDTO),
                        baseSendExtendDTO.getSendTimeout()
                );
            }

            log.info("[生产者] {} - 发送结果：{}，消息ID：{}，消息Keys：{}", baseSendExtendDTO.getEventName(), sendResult.getSendStatus(), sendResult.getMsgId(), baseSendExtendDTO.getKeys());
        } catch (Exception ex) {
            log.info("[生产者] {} - 消息发送失败，消息体：{}", baseSendExtendDTO.getEventName(), JSON.toJSONString(messageSendEvent), ex);
            throw ex;
        }

        return sendResult;
    }
}
