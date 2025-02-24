package com.xiaoshuai66.couponking.framework.idempotent;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: SpELUtil
 * Package: com.xiaoshuai66.couponking.framework.idempotent
 * Description: SpEL 表达式解析工具
 *
 * @Author 赵帅
 * @Create 2025/2/24 22:44
 * @Version 1.0
 */
public final class SpELUtil {

    /**
     * 校验并返回实际使用的 spEL 表达式
     *
     * @param spEl  spEL 表达式
     * @return  实际使用的 spEL 表达式
     */
    public static Object parseKey(String spEl, Method method, Object[] contextObj) {
        List<String> spELFlag = ListUtil.of("#", "T(");
        Optional<String> optional = spELFlag.stream().filter(spEl::contains).findFirst();
        if (optional.isPresent()) {
            return parse(spEl, method, contextObj);
        }
        return spEl;
    }

    /**
     * 转换参数为字符串
     *
     * @param spEL          spEl 表达式
     * @param contextObj    上下文对象
     * @return 解析的字符串值
     */
    public static Object parse(String spEL, Method method, Object[] contextObj) {
        // 用于获取方法参数名称
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        // Spring的SpEL解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 解析spEL
        Expression exp = parser.parseExpression(spEL);
        // 获取方法中的参数列表
        String[] params = discoverer.getParameterNames(method);
        // 创建评估上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        if (ArrayUtil.isNotEmpty(params)) {
            for (int len = 0; len < params.length; len++) {
                // 执行表达式解析
                context.setVariable(params[len], contextObj[len]);
            }
        }
        return exp.getValue(context);
    }
}
