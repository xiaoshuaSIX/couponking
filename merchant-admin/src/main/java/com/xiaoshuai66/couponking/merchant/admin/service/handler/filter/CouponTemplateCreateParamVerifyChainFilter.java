package com.xiaoshuai66.couponking.merchant.admin.service.handler.filter;

import cn.hutool.core.util.ObjectUtil;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.enums.DiscountTargetEnum;
import com.xiaoshuai66.couponking.merchant.admin.service.basics.chain.MerchantAdminAbstractChainHandler;
import org.springframework.stereotype.Component;

import static com.xiaoshuai66.couponking.merchant.admin.enums.ChainBizMarkEnum.MERCHANT_ADMIN_CREATE_COUPON_TEMPLATE_KEY;

/**
 * ClassName: CouponTemplateCreateParamVerifyChainFilter
 * Package: com.xiaoshuai66.couponking.merchant.admin.service.handler.filter
 * Description: 验证优惠券创建接口参数是否正确责任链｜验证参数数据是否正确
 *
 * @Author 赵帅
 * @Create 2025/2/10 17:09
 * @Version 1.0
 */
@Component
public class CouponTemplateCreateParamVerifyChainFilter implements MerchantAdminAbstractChainHandler<CouponTemplateSaveReqDTO> {

    @Override
    public void handler(CouponTemplateSaveReqDTO requestParam) {
        // 验证参数数据是否正确
        if (ObjectUtil.equal(requestParam.getTarget(), DiscountTargetEnum.PRODUCT_SPECIFIC)) {
            // 调用商品中台验证商品是否存在，如果不存在抛出异常
            // ......
        }
    }

    @Override
    public String mark() {
        return MERCHANT_ADMIN_CREATE_COUPON_TEMPLATE_KEY.name();
    }

    @Override
    public int getOrder() {
        return 20;
    }
}
