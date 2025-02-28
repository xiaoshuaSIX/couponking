package com.xiaoshuai66.couponking.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai66.couponking.engine.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateQueryReqDTO;
import com.xiaoshuai66.couponking.engine.dto.resp.CouponTemplateQueryRespDTO;

import java.util.List;

/**
 * @author zhaoshuai
 * @ClassName CouponTemplateService.java
 * @Description 优惠券模板业务逻辑层
 * @createTime 2025-02-17
 */
public interface CouponTemplateService extends IService<CouponTemplateDO> {

    /**
     * 查询优惠券模板
     *
     * @param requestParam 请求参数
     * @return 优惠券模板信息
     */
    CouponTemplateQueryRespDTO findCouponTemplate(CouponTemplateQueryReqDTO requestParam);

    /**
     * 根据优惠券id集合查询出券信息
     *
     * @param couponTemplateIds 优惠券id集合
     */
    List<CouponTemplateDO> listCouponTemplateByIds(List<Long> couponTemplateIds, List<Long> shopNumbers);
}
