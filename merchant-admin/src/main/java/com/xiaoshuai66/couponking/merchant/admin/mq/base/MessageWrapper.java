package com.xiaoshuai66.couponking.merchant.admin.mq.base;

import lombok.*;

import java.io.Serializable;

/**
 * ClassName: MessageWrapper
 * Package: com.xiaoshuai66.couponking.merchant.admin.mq.base
 * Description: 消息体包装器
 *
 * @Author 赵帅
 * @Create 2025/2/16 15:48
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageWrapper<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息发送 Keys
     */
    @NonNull
    private String keys;

    /**
     * 消息体
     */
    @NonNull
    private T message;

    /**
     * 消息发送时间
     */
    private Long timestamp = System.currentTimeMillis();
}
