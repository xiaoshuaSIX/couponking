package com.xiaoshuai66.couponking.framework.config;

import com.xiaoshuai66.couponking.framework.idempotent.NoDuplicateSubmitAspect;
import com.xiaoshuai66.couponking.framework.idempotent.NoMQDuplicateConsumeAspect;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * ClassName: IdempotentConfiguration
 * Package: com.xiaoshuai66.couponking.framework.config
 * Description: 幂等组件相关配置类
 * @Author 赵帅
 * @Create 2025/2/12 23:15 
 * @Version 1.0   
 */
public class IdempotentConfiguration {
    /**
     * 防止用户重复提交表单信息切面控制类
     */
    @Bean
    public NoDuplicateSubmitAspect noDuplicateSubmitAspect(RedissonClient redissonClient) {
        return new NoDuplicateSubmitAspect(redissonClient);
    }

    @Bean
    public NoMQDuplicateConsumeAspect noMQDuplicateConsumeAspect(StringRedisTemplate stringRedisTemplate) {
        return new NoMQDuplicateConsumeAspect(stringRedisTemplate);
    }
}
