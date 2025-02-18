package com.xiaoshuai66.couponking.distribution.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 赵帅
 * @ClassName: CouponStatusEnum.java
 * @Description: 优惠券使用状态枚举类
 * @Create 2025/2/18 15:42
 */
@RequiredArgsConstructor
public enum CouponStatusEnum {

    /**
     * 生效中
     */
    EFFECTIVE(0),

    /**
     * 已结束
     */
    ENDED(1);

    @Getter
    private final int type;
}
