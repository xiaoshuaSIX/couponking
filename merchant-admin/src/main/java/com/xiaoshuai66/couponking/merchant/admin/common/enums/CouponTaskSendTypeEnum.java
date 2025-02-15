package com.xiaoshuai66.couponking.merchant.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ClassName: CouponTaskSendTypeEnum
 * Package: com.xiaoshuai66.couponking.merchant.admin.common.enums
 * Description: 优惠券推送任务发送类型枚举类
 *
 * @Author 赵帅
 * @Create 2025/2/15 23:49
 * @Version 1.0
 */
@RequiredArgsConstructor
public enum CouponTaskSendTypeEnum {

    /**
     * 立即发送
     */
    IMMEDIATE(0),

    /**
     * 定时发送
     */
    SCHEDULED(1);

    @Getter
    private final int type;
}
