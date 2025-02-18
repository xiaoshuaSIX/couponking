package com.xiaoshuai66.couponking.distribution.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 赵帅
 * @ClassName: CouponSourceEnum.java
 * @Description: 优惠券来源枚举类
 * @Create 2025/2/18 14:26
 */
@RequiredArgsConstructor
public enum CouponSourceEnum {

    /**
     * 店铺券
     */
    SHOP(0),

    /**
     * 平台券
     */
    PLATFORM(1);

    @Getter
    private final int type;
}
