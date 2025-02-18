package com.xiaoshuai66.couponking.distribution.mq.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 赵帅
 * @ClassName: CouponTaskExecuteEvent.java
 * @Description: 优惠券推送任务执行时间
 * @Create 2025/2/18 11:29
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
