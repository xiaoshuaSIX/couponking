package com.xiaoshuai66.couponking.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai66.couponking.engine.dao.entity.CouponTemplateDO;
import com.xiaoshuai66.couponking.engine.dao.mapper.CouponTemplateMapper;
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
}
