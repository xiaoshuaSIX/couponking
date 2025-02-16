package com.xiaoshuai66.couponking.merchant.admin.mq.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: BaseSendExtendDTO
 * Package: com.xiaoshuai66.couponking.merchant.admin.mq.base
 * Description: 消息发送事件基础扩充属性实体
 *
 * @Author 赵帅
 * @Create 2025/2/16 15:23
 * @Version 1.0
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
    private String tag;

    /**
     * 业务标签
     */
    private String keys;

    /**
     * 发送消息超时时间
     */
    private Long sendTimeout;

    /**
     * 具体延迟时间
     */
    private Long delayTime;
}
