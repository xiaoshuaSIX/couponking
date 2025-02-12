package com.xiaoshuai66.couponking.merchant.admin.template.spel;

import cn.hutool.core.lang.Assert;
import com.xiaoshuai66.couponking.merchant.admin.common.context.UserContext;
import com.xiaoshuai66.couponking.merchant.admin.common.context.UserInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * ClassName: CouponTemplateLogSpELTests
 * Package: com.xiaoshuai66.couponking.merchant.admin.template.spel
 * Description: SpEL 表达式测试类
 *
 * @Author 赵帅
 * @Create 2025/2/12 14:08
 * @Version 1.0
 */
public class CouponTemplateLogSpELTests {

    /**
     * 调用静态类方法
     */
    @Test
    public void testSpELGetRandom(){
        String spELKey = "T(java.lang.Math).random()";
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(spELKey);
        Assert.isTrue(expression.getValue() instanceof Double);
    }

    /**
     * 调用静态类方法并运算
     */
    @Test
    public void testSpELGetRandomV2(){
        String spELKey = "T(java.lang.Math).random() * 100.0";
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(spELKey);
        Assert.isTrue(expression.getValue() instanceof Double);
    }

    /**
     * 调用当前用户静态类方法
     */
    @Test
    public void testSpELGetCurrentUser(){
        // 初始化数据
        String userid = "1810518709471555585";
        UserContext.setUser(new UserInfoDTO(userid, "pdd45305558318", 1810714735922956666L));

        // 调用用户上下文获取当前用户 ID
        String spELKey = "T(com.xiaoshuai66.couponking.merchant.admin.context.UserContext).getUserId()";
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(spELKey);
        try {
            Assert.equals(expression.getValue(), userid);
        } finally {
            UserContext.removeUser();
        }
    }

    /**
     * 调用当前登录用户静态类方法，如果为空取默认值
     */
    @Test
    public void testSpELGetCurrentUserDefaultValue(){
        // 调用用户上下文获取当前用户 ID，如果为空，取默认值
        String spELKey = "T(com.xiaoshuai66.couponking.merchant.admin.context.UserContext).getUserId() ?: 'zhaoshuai'";
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(spELKey);
        Assert.equals(expression.getValue(), "zhaoshuai");
    }
}
