package com.xiaoshuai66.couponking.merchant.admin.mq.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CouponTemplateDelayEvent
 * Package: com.xiaoshuai66.couponking.merchant.admin.mq.event
 * Description: 优惠券模版定时任务
 *
 * @Author 赵帅
 * @Create 2025/2/16 20:36
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponTemplateDelayEvent {

    /**
     * 店铺id
     */
    private Long shopNumber;

    /**
     * 优惠券模版id
     */
    private Long couponTemplateId;

    /**
     * 具体延迟时间
     */
    private Long delayTime;
}
