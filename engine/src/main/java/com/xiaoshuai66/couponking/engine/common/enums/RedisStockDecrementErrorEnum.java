package com.xiaoshuai66.couponking.engine.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Description: Redis 扣减优惠券库存错误枚举
 * @author 赵帅
 * @Create 2025/2/25 15:48
 */
@RequiredArgsConstructor
public enum RedisStockDecrementErrorEnum {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 库存不足
     */
    STOCK_INSUFFICIENT(1, "优惠券已被领取完啦"),

    /**
     * 用户已经达到领取上线
     */
    LIMIT_REACHED(2, "用户已经达到领取上限");


    @Getter
    private final long code;
    @Getter
    private final String message;

    /**
     * 根据 code 找到对应的枚举实例判断是否成功标识
     *
     * @param code 要查找的编码
     * @return 是否成功标识
     */
    public static boolean isFail(long code) {
        for (RedisStockDecrementErrorEnum status : RedisStockDecrementErrorEnum.values()) {
            if (status.getCode() == code) {
                return status != SUCCESS;
            }
        }
        return false;
    }

    /**
     * 根据 type 找到对应的枚举实例
     *
     * @param code 要查找的编码
     * @return 对应的枚举实例
     */
    public static String fromType(long code) {
        for (RedisStockDecrementErrorEnum method : RedisStockDecrementErrorEnum.values()) {
            if (method.getCode() == code) {
                return method.getMessage();
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
