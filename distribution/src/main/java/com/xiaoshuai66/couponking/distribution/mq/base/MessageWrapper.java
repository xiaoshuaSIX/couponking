package com.xiaoshuai66.couponking.distribution.mq.base;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author 赵帅
 * @ClassName: MessageWrapper.java
 * @Description: 消息包装体
 * @Create 2025/2/18 11:24
 */
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageWrapper<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息发生 Keys
     */
    @NonNull
    private String keys;

    /**
     * 消息体
     */
    @NonNull
    private T message;

    /**
     * 唯一标识，用于客户端幂等验证
     */
    private String uuid = UUID.randomUUID().toString();

    /**
     * 消息发送时间
     */
    private Long timestamp = System.currentTimeMillis();
}
