package com.xiaoshuai66.couponking.merchant.admin.service;

import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;

/**
 * ClassName: CouponTemplateService
 * Package: com.xiaoshuai66.couponking.merchant.admin.service
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/9 22:59
 * @Version 1.0
 */
public interface CouponTemplateService {
    /**
     * 商家创建优惠券模版
     *
     * @param requestParam 请求参数
     */
    void createCouponTemplate(CouponTemplateSaveReqDTO requestParam);
}
