package com.xiaoshuai66.couponking.engine.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CouponTemplateQueryReqDTO
 * Package: com.xiaoshuai66.couponking.engine.dto.req
 * Description: 优惠券模板查询接口请求参数实体
 *
 * @Author 赵帅
 * @Create 2025/2/17 9:59
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "优惠券模板查询请求参数实体")
public class CouponTemplateQueryReqDTO {

    /**
     * 店铺编号
     */
    @Schema(description = "店铺编号", example = "1810714735922956666", required = true)
    private String shopNumber;

    /**
     * 优惠券模板id
     */
    @Schema(description = "优惠券模板id", example = "1891175172434841602", required = true)
    private String couponTemplateId;
}
