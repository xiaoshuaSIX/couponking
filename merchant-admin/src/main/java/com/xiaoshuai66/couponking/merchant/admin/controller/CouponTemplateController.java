package com.xiaoshuai66.couponking.merchant.admin.controller;

import com.xiaoshuai66.couponking.framework.idempotent.NoDuplicateSubmit;
import com.xiaoshuai66.couponking.framework.result.Result;
import com.xiaoshuai66.couponking.framework.web.Results;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.service.CouponTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CouponTemplateController
 * Package: com.xiaoshuai66.couponking.merchant.admin.controller
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/9 22:56
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "优惠券模版管理")
public class CouponTemplateController {
    private final CouponTemplateService couponTemplateService;

    @NoDuplicateSubmit
    @Operation(summary = "商家创建优惠券模版")
    @PostMapping("/api/merchant-admin/coupon-template/create")
    public Result<Void> createCouponTemplate(@RequestBody CouponTemplateSaveReqDTO requestParam) {
        couponTemplateService.createCouponTemplate(requestParam);
        return Results.success();
    }
}
