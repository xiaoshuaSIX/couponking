package com.xiaoshuai66.couponking.engine.controller;

import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRedeemReqDTO;
import com.xiaoshuai66.couponking.engine.service.UserCouponService;
import com.xiaoshuai66.couponking.framework.result.Result;
import com.xiaoshuai66.couponking.framework.web.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 用户优惠券控制层
 * @author 赵帅
 * @Create 2025/2/25 13:58
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "用户优惠券管理")
public class UserCouponController {

    private  final UserCouponService userCouponService;

    @Operation(summary = "兑换优惠券模板", description = "存在较高流量场景，可类比“秒杀”业务")
    @PostMapping("/api/engine/user-coupon/redeem")
    public Result<Void> redeemUserCoupon(@RequestBody CouponTemplateRedeemReqDTO requestParam) {
        userCouponService.redeemUserCoupon(requestParam);
        return Results.success();
    }
}
