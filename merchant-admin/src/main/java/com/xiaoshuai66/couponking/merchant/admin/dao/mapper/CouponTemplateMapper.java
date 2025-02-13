package com.xiaoshuai66.couponking.merchant.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTemplateDO;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: CouponTemplateMapper
 * Package: com.xiaoshuai66.couponking.merchant.admin.dao.mapper
 * Description: 优惠券模版数据库持久层
 *
 * @Author 赵帅
 * @Create 2025/2/10 01:35
 * @Version 1.0
 */
public interface CouponTemplateMapper extends BaseMapper<CouponTemplateDO> {

    /**
     * 增加优惠券模板发行量
     *
     * @param shopNumber        店铺编号
     * @param couponTemplateId  优惠券模板 ID
     * @param number            增加发行数量
     */
    int increaseNumberCouponTemplate(@Param("shopNumber") Long shopNumber, @Param("couponTemplateId") String couponTemplateId, @Param("number") Integer number);
}
