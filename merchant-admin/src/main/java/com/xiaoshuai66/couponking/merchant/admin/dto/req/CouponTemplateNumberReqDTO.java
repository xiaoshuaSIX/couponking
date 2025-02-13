package com.xiaoshuai66.couponking.merchant.admin.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ClassName: CouponTemplateNumberReqDTO
 * Package: com.xiaoshuai66.couponking.merchant.admin.dto.req
 * Description: 优惠券模板增加发行量请求参数实体
 *
 * @Author 赵帅
 * @Create 2025/2/13 14:58
 * @Version 1.0
 */
@Data
@Schema(description = "优惠券模板增加发行量请求参数")
public class CouponTemplateNumberReqDTO {

    /**
     * 优惠券模板id
     */
    @Schema(description = "优惠券模板id",
            example = "1810966706881941507",
            required = true)
    private String couponTemplateId;

    /**
     * 增加发行数量
     */
    @Schema(description = "增加发行数量",
            example = "100",
            required = true)
    private Integer number;
}
