package com.xiaoshuai66.couponking.engine.common;

/**
 * @author zhaoshuai
 * @ClassName EngineRedisConstant.java
 * @Description 分布式 Redis 缓存引擎层常量类
 * @createTime 2025-02-17
 */
public class EngineRedisConstant {

    /**
     * 优惠券模板缓存 Key
     */
    public static final String COUPON_TEMPLATE_KEY = "coupon-king_engine:template:%s";

    /**
     * 优惠券模板缓存分布式锁 Key
     */
    public static final String LOCK_COUPON_TEMPLATE_KEY = "coupon-king_engine:lock:template:%s";
}
