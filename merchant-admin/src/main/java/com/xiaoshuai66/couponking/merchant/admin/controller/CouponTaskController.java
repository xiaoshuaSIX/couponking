package com.xiaoshuai66.couponking.merchant.admin.controller;

import com.xiaoshuai66.couponking.framework.idempotent.NoDuplicateSubmit;
import com.xiaoshuai66.couponking.framework.result.Result;
import com.xiaoshuai66.couponking.framework.web.Results;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTaskCreateReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.service.CouponTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CouponTaskController
 * Package: com.xiaoshuai66.couponking.merchant.admin.controller
 * Description: 优惠券推送任务控制层
 *
 * @Author 赵帅
 * @Create 2025/2/16 00:08
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "优惠券推送任务管理")
public class CouponTaskController {

    private final CouponTaskService couponTaskService;

    @Operation(summary = "创建优惠券推送任务")
    @NoDuplicateSubmit(message = "请勿短时间内重复提交优惠券推送任务")
    @PostMapping("/api/merchant-admin/coupon-task/create")
    public Result<Void> createCouponTask(@RequestBody CouponTaskCreateReqDTO requestParam) {
        couponTaskService.createCouponTask(requestParam);
        return Results.success();
    }
}
