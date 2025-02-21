package com.xiaoshuai66.couponking.distribution.common.constant;

/**
 * @author zhaoshuai
 * @ClassName EngineRedisConstant.java
 * @Description 分布式 Redis 缓存引擎层常量类
 * @createTime 2025-02-17
 */
public final class EngineRedisConstant {

    /**
     * 优惠券模板缓存 Key
     */
    public static final String COUPON_TEMPLATE_KEY = "coupon-king_engine:template:%s";

    /**
     * 用户已领取优惠券列表模板 Key
     */
    public static final String USER_COUPON_TEMPLATE_LIST_KEY = "coupon-king_engine:user-template-list:%s";

}
