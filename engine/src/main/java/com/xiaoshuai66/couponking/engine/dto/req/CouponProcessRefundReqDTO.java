package com.xiaoshuai66.couponking.engine.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ClassName: CouponProcessRefundReqDTO
 * Package: com.xiaoshuai66.couponking.engine.dto.req
 * Description: 处理优惠券结算单退款请求参数书体
 *
 * @Author 赵帅
 * @Create 2025/3/1 10:41
 * @Version 1.0
 */
@Data
public class CouponProcessRefundReqDTO {

    /**
     * 优惠券ID
     */
    @Schema(description = "优惠券ID", required = true)
    private Long couponId;
}
