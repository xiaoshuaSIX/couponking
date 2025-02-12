package com.xiaoshuai66.couponking.framework.idempotent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: NoDuplicateSubmit
 * Package: com.xiaoshuai66.couponking.framework.idempotent
 * Description: 幂等注解，防止用户重复提交表单信息
 *
 * @Author 赵帅
 * @Create 2025/2/12 21:57
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoDuplicateSubmit {

    /**
     * 触发幂等失败逻辑时，返回到错误提示信息
     */
    String message() default "您操作的太快哦，请稍后再试";
}
