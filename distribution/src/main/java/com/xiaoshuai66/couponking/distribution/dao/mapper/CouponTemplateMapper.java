package com.xiaoshuai66.couponking.distribution.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshuai66.couponking.distribution.dao.entity.CouponTemplateDO;
import org.springframework.data.repository.query.Param;

/**
 * @author 赵帅
 * @ClassName: CouponTemplateMapper.java
 * @Description:
 * @Create 2025/2/18 14:36
 */
public interface CouponTemplateMapper extends BaseMapper<CouponTemplateDO> {

    /**
     * 自减优惠券模板库存
     *
     * @param shopNumber 店铺号
     * @param couponTemplateId 优惠券模板 ID
     * @param decrementStock 扣减库存数量
     * @return 是否发生变更记录
     */
    int decrementCouponTemplateStock(@Param("shopNumber") Long shopNumber, @Param("couponTemplateId") Long couponTemplateId, @Param("decrementStock") Integer decrementStock);
}
