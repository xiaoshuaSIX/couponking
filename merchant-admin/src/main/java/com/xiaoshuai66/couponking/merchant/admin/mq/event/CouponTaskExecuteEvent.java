package com.xiaoshuai66.couponking.merchant.admin.mq.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CouponTaskExecuteEvent
 * Package: com.xiaoshuai66.couponking.merchant.admin.mq.event
 * Description: 优惠券推送任务执行事件
 *
 * @Author 赵帅
 * @Create 2025/2/16 19:39
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponTaskExecuteEvent {

    /**
     * 推送任务id
     */
    private Long couponTaskId;
}
