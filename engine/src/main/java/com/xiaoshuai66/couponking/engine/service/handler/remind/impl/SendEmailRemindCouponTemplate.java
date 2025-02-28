package com.xiaoshuai66.couponking.engine.service.handler.remind.impl;

import com.xiaoshuai66.couponking.engine.service.handler.remind.RemindCouponTemplate;
import com.xiaoshuai66.couponking.engine.service.handler.remind.dto.CouponTemplateRemindDTO;
import org.springframework.stereotype.Component;

/**
 * @className: SendEmailRemindCouponTemplate
 * @Description: 发送邮件的方式提醒用户抢券
 * @author: zhaoshuai
 * @date: 2025/2/28 10:33
 */
@Component
public class SendEmailRemindCouponTemplate implements RemindCouponTemplate {

    /**
     * 以邮件方式提醒用户抢券
     *
     * @param couponTemplateRemindDTO 提醒所需要的信息
     */
    @Override
    public boolean remind(CouponTemplateRemindDTO couponTemplateRemindDTO) {
        // 空实现
        return false;
    }
}
