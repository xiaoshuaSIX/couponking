package com.xiaoshuai66.couponking.engine.controller;

import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRemindCreateReqDTO;
import com.xiaoshuai66.couponking.engine.service.CouponTemplateRemindService;
import com.xiaoshuai66.couponking.framework.idempotent.NoDuplicateSubmit;
import com.xiaoshuai66.couponking.framework.result.Result;
import com.xiaoshuai66.couponking.framework.web.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: CouponTemplateRemindController
 * @Description: 优惠券模板控制层
 * @author: 赵帅
 * @date: 2025/2/27 14:32
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "优惠券预约提醒管理")
public class CouponTemplateRemindController {

    private final CouponTemplateRemindService couponTemplateRemindService;

    @Operation(summary = "发出优惠券预约提醒请求")
    @NoDuplicateSubmit(message = "请勿短时间内重复提交预约提醒请求")
    public Result<Void> createCouponRemind(@RequestBody CouponTemplateRemindCreateReqDTO requestParam) {
        couponTemplateRemindService.createCouponRemind(requestParam);
        return Results.success();
    }
}
