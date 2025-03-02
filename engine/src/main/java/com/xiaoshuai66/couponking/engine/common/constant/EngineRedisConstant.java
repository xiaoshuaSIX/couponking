package com.xiaoshuai66.couponking.engine.common.constant;

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

    /**
     * 优惠券模版缓存空值 Key
     */
    public static final String COUPON_TEMPLATE_IS_NULL_KEY = "coupon-king_engine:template_isnull:%s";

    /**
     * 限制用户领取优惠券模板次数缓存 Key
     */
    public static final String USER_COUPON_TEMPLATE_LIMIT_KEY = "coupon-king_engine:user-template-limit:%s_%s";

    /**
     * 用户已领取优惠券列表模板 Key
     */
    public static final String USER_COUPON_TEMPLATE_LIST_KEY = "coupon-king_engine:user-template-list:%s";

    /**
     * 检查用户是否已提醒 Key
     */
    public static final String COUPON_REMIND_CHECK_KEY = "coupon-king_engine:coupon-remind-check:%s_%s_%d_%d";

    /**
     * 用户预约提醒信息 Key
     */
    public static final String USER_COUPON_TEMPLATE_REMIND_INFORMATION = "coupon-king_engine:coupon-remind-information:%s";

    /**
     * 优惠券结算单分布式锁 Key
     */
    public static final String LOCK_COUPON_SETTLEMENT_KEY = "coupon-king_engine:lock:coupon-settlement:%d";

}
