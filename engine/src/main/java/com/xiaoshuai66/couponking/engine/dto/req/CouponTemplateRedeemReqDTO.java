package com.xiaoshuai66.couponking.engine.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description: 兑换优惠券请求参数实体
 * @author 赵帅
 * @Create 2025/2/25 14:25
 */
@Data
@Schema(description = "兑换优惠券请求参数实体")
public class CouponTemplateRedeemReqDTO {
    /**
     * 券来源 0：领券中心 1：平台发放 2：店铺领取
     */
    @Schema(description = "券来源", example = "0", required = true)
    private Integer source;

    /**
     * 店铺编号
     */
    @Schema(description = "店铺编号", example = "1810714735922956666", required = true)
    private String shopNumber;

    /**
     * 优惠券模板id
     */
    @Schema(description = "优惠券模板id", example = "1810966706881941507", required = true)
    private String couponTemplateId;
}
