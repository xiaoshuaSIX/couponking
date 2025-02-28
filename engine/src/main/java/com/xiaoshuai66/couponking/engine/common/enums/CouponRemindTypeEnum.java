package com.xiaoshuai66.couponking.engine.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @className: CouponRemindTypeEnum
 * @Description: 预约提醒方式枚举类，值必须是0，1，2，3......
 * @author: zhaoshuai
 * @date: 2025/2/28 10:05
 */
@RequiredArgsConstructor
public enum CouponRemindTypeEnum {

    /**
     * APP 通知
     */
    APP(0, "APP通知"),

    /**
     * 邮件提醒
     */
    EMAIL(1, "邮件提醒");

    @Getter
    private final int type;
    @Getter
    private final String describe;

    public static CouponRemindTypeEnum getByType(Integer type) {
        for (CouponRemindTypeEnum remindEnum : values()) {
            if (remindEnum.getType() == type) {
                return remindEnum;
            }
        }
        return null;
    }

    public static String getDescribeByType(Integer type) {
        for (CouponRemindTypeEnum remindEnum : values()) {
            if (remindEnum.getType() == type) {
                return remindEnum.getDescribe();
            }
        }
        return null;
    }
}
