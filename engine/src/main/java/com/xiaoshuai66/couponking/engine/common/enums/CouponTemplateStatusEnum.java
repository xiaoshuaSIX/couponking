package com.xiaoshuai66.couponking.engine.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ClassName: CouponTemplateStatusEnum
 * Package: com.xiaoshuai66.couponking.engine.common.enums
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/10 01:51
 * @Version 1.0
 */
@RequiredArgsConstructor
public enum CouponTemplateStatusEnum {
    /**
     * 0：表示优惠券处于生效中的状态
     */
    ACTIVE(0),

    /**
     * 1：表示优惠券已经结束，不可再使用
     */
    ENDED(1);

    @Getter
    private final int status;
}
