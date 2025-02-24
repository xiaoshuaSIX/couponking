package com.xiaoshuai66.couponking.framework.idempotent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * ClassName: IdempotentMQConsumeStatusEnum
 * Package: com.xiaoshuai66.couponking.framework.idempotent
 * Description: 幂等 MQ 消费状态枚举
 *
 * @Author 赵帅
 * @Create 2025/2/24 20:59
 * @Version 1.0
 */
@RequiredArgsConstructor
public enum IdempotentMQConsumeStatusEnum {

    /**
     * 消费中
     */
    CONSUMING("0"),

    /**
     * 已消费
     */
    CONSUMED("1");

    @Getter
    private final String code;

    /**
     * 如果消费状态等于消费中，返回失败
     *
     * @param consumeStatus 消费状态
     * @return 是否消费
     */
    public static boolean isError(String consumeStatus) {
        return Objects.equals(CONSUMING.getCode(), consumeStatus);
    }
}
