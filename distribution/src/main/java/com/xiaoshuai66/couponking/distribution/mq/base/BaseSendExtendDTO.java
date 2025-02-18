package com.xiaoshuai66.couponking.distribution.mq.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 赵帅
 * @ClassName: BaseSendExtendDTO.java
 * @Description: 消息发送事件基础扩充属性实体
 * @Create 2025/2/18 21:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseSendExtendDTO {

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
    private String keys;

    /**
     * 发送消息超时时间
     */
    private Long sentTimeout;

    /**
     * 具体延迟时间
     */
    private Long delayTime;
}
