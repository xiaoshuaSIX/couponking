package com.xiaoshuai66.couponking.engine.service.handler.remind.impl;

import com.xiaoshuai66.couponking.engine.service.handler.remind.RemindCouponTemplate;
import com.xiaoshuai66.couponking.engine.service.handler.remind.dto.CouponTemplateRemindDTO;
import org.springframework.stereotype.Component;

/**
 * @className: SendAppMessageRemindCouponTemplate
 * @Description: 应用 App 弹框方式提醒用户抢券
 * @author: zhaoshuai
 * @date: 2025/2/28 10:30
 */
@Component
public class SendAppMessageRemindCouponTemplate implements RemindCouponTemplate {

    @Override
    public boolean remind(CouponTemplateRemindDTO couponTemplateRemindDTO) {
        // 空实现
        return false;
    }
}
