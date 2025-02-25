package com.xiaoshuai66.couponking.engine.mq.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 消息发送事件基础扩充属性实体
 * @author 赵帅
 * @Create 2025/2/25 17:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class BaseSendExtendDTO {

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 主题
     */
    private String topic;

    /**
     * 标签
     */
    private String tag;

    /**
     * 业务标识
     */
    private String keys;

    /**
     * 消息发送超时事件
     */
    private Long sentTimeout;

    /**
     * 具体延迟时间
     */
    private Long delayTime;
}
