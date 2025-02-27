package com.xiaoshuai66.couponking.engine.toolkit;

import com.xiaoshuai66.couponking.framework.exception.ClientException;

/**
 * @className: CouponTemplateRemindUtil
 * @Description: 优惠券预约提醒工具类
 * @author: zhaoshuai
 * @date: 2025/2/27 15:18
 */
public class CouponTemplateRemindUtil {

    /**
     * 下一个类型的位移量，每个类型占用12个bit，共计60分钟
     */
    private static final int NEXT_TYPE_BITS = 12;

    /**
     * 5分钟为一个间隔
     */
    private static final int TIME_INTERVAL = 5;

    /**
     * 根据预约时间和预约类型计算bitmap
     */
    public static Long calculateBitMap(Integer remindTime, Integer type) {
        if (remindTime > TIME_INTERVAL * NEXT_TYPE_BITS) {
            throw new ClientException("预约提醒的时间不能早于开票前" + TIME_INTERVAL * NEXT_TYPE_BITS + "分钟");
        }
        return 1L << (type * NEXT_TYPE_BITS + Math.max(0, remindTime / TIME_INTERVAL - 1));
    }
}
