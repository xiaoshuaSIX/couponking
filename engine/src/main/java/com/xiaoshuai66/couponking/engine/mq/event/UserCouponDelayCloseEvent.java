package com.xiaoshuai66.couponking.engine.mq.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户优惠券延时关闭事件
 * @author 赵帅
 * @Create 2025/2/25 22:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponDelayCloseEvent {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户优惠券id
     */
    private String userCouponId;

    /**
     * 优惠券模板id
     */
    private String couponTemplateId;

    /**
     * 具体延迟时间
     */
    private Long delayTime;
}
