package com.xiaoshuai66.couponking.merchant.admin.service.handler.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xiaoshuai66.couponking.framework.exception.ClientException;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.service.basics.chain.MerchantAdminAbstractChainHandler;
import jodd.util.StringUtil;
import org.springframework.stereotype.Component;

import static com.xiaoshuai66.couponking.merchant.admin.common.enums.ChainBizMarkEnum.MERCHANT_ADMIN_CREATE_COUPON_TEMPLATE_KEY;

/**
 * ClassName: CouponTemplateCreateParamNotNullChainFilter
 * Package: com.xiaoshuai66.couponking.merchant.admin.service.handler.filter
 * Description: 验证优惠券创建接口参数是否正确责任链｜验证必填参数是否为空或空的字符串
 *
 * @Author 赵帅
 * @Create 2025/2/10 16:48
 * @Version 1.0
 */
@Component
public class CouponTemplateCreateParamNotNullChainFilter implements MerchantAdminAbstractChainHandler<CouponTemplateSaveReqDTO> {
    @Override
    public void handler(CouponTemplateSaveReqDTO requestParam) {
        // 验证必填参数是否为空或空的字符串
        if (StrUtil.isEmpty(requestParam.getName())) {
            throw new ClientException("优惠券名称不能为空");
        }

        if (ObjectUtil.isEmpty(requestParam.getSource())) {
            throw new ClientException("优惠券来源不能为空");
        }

        if (ObjectUtil.isEmpty(requestParam.getTarget())) {
            throw new ClientException("优惠对象不能为空");
        }

        if (ObjectUtil.isEmpty(requestParam.getType())) {
            throw new ClientException("优惠类型不能为空");
        }

        if (ObjectUtil.isEmpty(requestParam.getValidStartTime())) {
            throw new ClientException("有效期开始时间不能为空");
        }

        if (ObjectUtil.isEmpty(requestParam.getValidEndTime())) {
            throw new ClientException("有效期结束时间不能为空");
        }

        if (ObjectUtil.isEmpty(requestParam.getStock())) {
            throw new ClientException("库存不能为空");
        }

        if (StringUtil.isEmpty(requestParam.getReceiveRule())) {
            throw new ClientException("领取规则不能为空");
        }

        if (StringUtil.isEmpty(requestParam.getConsumeRule())) {
            throw new ClientException("消耗规则不能为空");
        }
    }

    @Override
    public String mark() {
        return MERCHANT_ADMIN_CREATE_COUPON_TEMPLATE_KEY.name();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
