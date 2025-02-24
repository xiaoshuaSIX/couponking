package com.xiaoshuai66.couponking.framework.idempotent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: NoMQDuplicateConsume
 * Package: com.xiaoshuai66.couponking.framework.idempotent
 * Description: 幂等注解，防止消息队列消费者重复消费消息
 *
 * @Author 赵帅
 * @Create 2025/2/24 21:05
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoMQDuplicateConsume {

    /**
     * 设置防重令牌 Key 前缀
     */
    String keyPrefix() default "";

    /**
     * 通过 SpEL 表达式生成的唯一 Key
     */
    String key();

    /**
     * 设置防重令牌 Key 过期时间，单位秒，默认 1 小时
     */
    long keyTimeout() default 3600L;
}
