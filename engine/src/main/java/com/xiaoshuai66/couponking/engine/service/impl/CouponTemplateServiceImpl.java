package com.xiaoshuai66.couponking.engine.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai66.couponking.engine.common.enums.CouponTemplateStatusEnum;
import com.xiaoshuai66.couponking.engine.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.engine.dao.mapper.CouponTemplateMapper;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateQueryReqDTO;
import com.xiaoshuai66.couponking.engine.dto.resp.CouponTemplateQueryRespDTO;
import com.xiaoshuai66.couponking.engine.service.CouponTemplateService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @ClassName CouponTemplateServiceImpl.java
 * @Description 优惠券模板业务逻辑实现曾
 * @author zhaoshuai
 * @createTime 2025-02-17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CouponTemplateServiceImpl extends ServiceImpl<CouponTemplateMapper, CouponTemplateDO> implements CouponTemplateService {

    private final CouponTemplateMapper couponTemplateMapper;

    @Override
    public CouponTemplateQueryRespDTO findCouponTemplate(CouponTemplateQueryReqDTO requestParam) {
        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .eq(CouponTemplateDO::getShopNumber, Long.parseLong(requestParam.getShopNumber()))
                .eq(CouponTemplateDO::getId, Long.parseLong(requestParam.getCouponTemplateId()))
                .eq(CouponTemplateDO::getStatus, CouponTemplateStatusEnum.ACTIVE.getStatus());
        CouponTemplateDO couponTemplateDO = couponTemplateMapper.selectOne(queryWrapper);
        return BeanUtil.toBean(couponTemplateDO, CouponTemplateQueryRespDTO.class);
    }
}
