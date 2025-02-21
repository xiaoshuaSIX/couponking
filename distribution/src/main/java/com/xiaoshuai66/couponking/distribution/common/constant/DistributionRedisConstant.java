package com.xiaoshuai66.couponking.distribution.common.constant;

/**
 * @author 赵帅
 * @ClassName: DistributionRedisConstant.java
 * @Description: 分发优惠券模板 Redis 缓存常量类
 * @Create 2025/2/20 13:49
 */
public final class DistributionRedisConstant {

    /**
     * 优惠券模板推送执行进度 Key
     */
    public static final String TEMPLATE_TASK_EXECUTE_PROGRESS_KEY = "coupon-king_distribution:template-task-execute-progress:%s";

    /**
     * 批量保存领取用户券用户 Key
     */
    public static final String TEMPLATE_TASK_EXECUTE_BATCH_USER_KEY = "coupon-king_distribution:template-task-execute-batch-user:%s";
}
