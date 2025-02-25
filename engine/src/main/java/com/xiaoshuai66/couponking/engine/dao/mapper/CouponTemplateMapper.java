package com.xiaoshuai66.couponking.engine.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshuai66.couponking.engine.dao.entity.CouponTemplateDO;
import org.springframework.data.repository.query.Param;

/**
 * ClassName: CouponTemplateMapper
 * Package: com.xiaoshuai66.couponking.engine.dao.mapper
 * Description: 优惠券模版数据库持久层
 *
 * @Author 赵帅
 * @Create 2025/2/10 01:35
 * @Version 1.0
 */
public interface CouponTemplateMapper extends BaseMapper<CouponTemplateDO> {

    /**
     * 自减优惠券模板库存
     *
     * @param couponTemplateId 优惠券模板 ID
     * @return 是否发生记录变更
     */
    int decrementCouponTemplateStock(@Param("shopNumber") Long shopNumber, @Param("couponTemplateId") Long couponTemplateId, @Param("decrementStock") Long decrementStock);
}
