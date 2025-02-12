package com.xiaoshuai66.couponking.merchant.admin.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ClassName: DiscountTargetEnum
 * Package: com.xiaoshuai66.couponking.merchant.admin.enums
 * Description: 优惠券优惠枚举类
 *
 * @Author 赵帅
 * @Create 2025/2/10 00:59
 * @Version 1.0
 */
@RequiredArgsConstructor
public enum DiscountTargetEnum {
    /**
     * 商品专属优惠
     */
    PRODUCT_SPECIFIC(0, "商品专属优惠"),
    /**
     * 全店通用优惠
     */
    ALL_STORE_GENERAL(1, "全店通用优惠");

    @Getter
    private final int type;

    @Getter
    private final String value;

    /**
     * 根据 type 找到对应的 value
     *
     * @param type 要查找的类型带嘛
     * @return 对应的描述值，如果没有找到抛异常
     */
    public static String findValueByType(int type) {
        for (DiscountTargetEnum target : DiscountTargetEnum.values()) {
            if (target.getType() == type) {
                return target.getValue();
            }
        }
        throw new IllegalArgumentException();
    }
}
