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
}
