package com.xiaoshuai66.couponking.engine.service;

import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRedeemReqDTO;

/**
 * @Description: 用户优惠券业务逻辑层
 * @author 赵帅
 * @Create 2025/2/25 14:00
 */
public interface UserCouponService {

    /**
     * 用户兑换优惠券
     *
     * @param requestParam 请求参数
     */
    void redeemUserCoupon(CouponTemplateRedeemReqDTO requestParam);

    /**
     * 用户兑换优惠券消息队列版本
     * 支持更高的并发，完全依赖缓存做前置校验，如果缓存认为没有问题，直接返回用户请求成功，在消息队列中做扣减等一系列流程
     *
     * @param requestParam 请求参数
     */
    void redeemUserCouponByMQ(CouponTemplateRedeemReqDTO requestParam);
}
