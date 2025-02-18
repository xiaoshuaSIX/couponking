package com.xiaoshuai66.couponking.distribution.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ClassName: CouponTaskStatusEnum
 * Package: com.xiaoshuai66.couponking.merchant.admin.common.enums
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/15 23:52
 * @Version 1.0
 */
@RequiredArgsConstructor
public enum CouponTaskStatusEnum {

    /**
     * 待执行
     */
    PENDING(0),

    /**
     * 执行中
     */
    IN_PROGRESS(1),

    /**
     * 执行失败
     */
    FAILED(2),

    /**
     * 执行成功
     */
    SUCCESS(3),

    /**
     * 取消
     */
    CANAL(4);

    @Getter
    private final int status;
}
