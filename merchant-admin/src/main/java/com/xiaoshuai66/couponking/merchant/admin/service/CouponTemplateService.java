package com.xiaoshuai66.couponking.merchant.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateNumberReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplatePageQueryReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTemplateSaveReqDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.resp.CouponTemplatePageQueryRespDTO;
import com.xiaoshuai66.couponking.merchant.admin.dto.resp.CouponTemplateQueryRespDTO;

/**
 * ClassName: CouponTemplateService
 * Package: com.xiaoshuai66.couponking.merchant.admin.service
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/9 22:59
 * @Version 1.0
 */
public interface CouponTemplateService extends IService<CouponTemplateDO> {
    /**
     * 商家创建优惠券模版
     *
     * @param requestParam 请求参数
     */
    void createCouponTemplate(CouponTemplateSaveReqDTO requestParam);

    /**
     * 分页查询商家优惠券模板
     *
     * @param requestParam 请求参数
     * @return 商家优惠券模板分页数据
     */
    IPage<CouponTemplatePageQueryRespDTO> pageQueryCouponTemplate(CouponTemplatePageQueryReqDTO requestParam);

    /**
     * 查询优惠券模板详情
     * 后管接口不存在并发，直接查询数据库即可
     *
     * @param couponTemplateId 优惠券模板 ID
     * @return 优惠券模板详情
     */
    CouponTemplateQueryRespDTO findCouponTemplateById(String couponTemplateId);

    /**
     * 增加优惠券模板发行量
     *
     * @param requestParam 请求参数
     */
    void increaseNumberCouponTemplate(CouponTemplateNumberReqDTO requestParam);

    /**
     * 结束优惠券模板
     *
     * @param couponTemplateId 优惠券模板 ID
     */
    void terminateCouponTemplate(String couponTemplateId);
}
