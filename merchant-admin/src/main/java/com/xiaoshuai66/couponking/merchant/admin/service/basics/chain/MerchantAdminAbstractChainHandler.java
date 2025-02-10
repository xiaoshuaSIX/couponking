package com.xiaoshuai66.couponking.merchant.admin.service.basics.chain;

import org.springframework.core.Ordered;

/**
 * ClassName: MerchantAdminAbstractChainHandler
 * Package: com.xiaoshuai66.couponking.merchant.admin.service.basics.chain
 * Description: 抽象商家后管业务责任链组件
 *
 * @Author 赵帅
 * @Create 2025/2/10 15:43
 * @Version 1.0
 */
public interface MerchantAdminAbstractChainHandler<T> extends Ordered {

    /**
     * 执行责任链逻辑
     *
     * @param requestParam 责任链执行入参
     */
    void handler(T requestParam);

    /**
     * @return 责任链组件标识
     */
    String mark();
}
