package com.xiaoshuai66.couponking.engine.service.handler.remind;

import com.xiaoshuai66.couponking.engine.service.handler.remind.dto.CouponTemplateRemindDTO;

/**
 * @className: RemindCouponTemplate
 * @Description: 优惠券抢券提醒接口
 * @author: zhaoshuai
 * @date: 2025/2/28 10:31
 */
public interface RemindCouponTemplate {

    /**
     * 提醒用户抢券
     *
     * @param couponTemplateRemindDTO 提醒所需要的信息
     */
    boolean remind(CouponTemplateRemindDTO couponTemplateRemindDTO);
}
