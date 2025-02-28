package com.xiaoshuai66.couponking.engine.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: CouponTemplateRemindQueryReqDTO
 * @Description: 查询抢券预约提醒接口请求参数实体
 * @author: zhaoshuai
 * @date: 2025/2/28 15:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "查询优惠券优惠抢券提醒参数实体")
public class CouponTemplateRemindQueryReqDTO {

    /**
     * 用户id
     */
    @Schema(description = "用户id", example = "1810518709471555585", required = true)
    private String userId;
}
