package com.xiaoshuai66.couponking.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai66.couponking.engine.dao.entity.CouponTemplateRemindDO;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRemindCreateReqDTO;

/**
 * @className: CouponTemplateRemindService
 * @Description: 优惠券预约提醒业务逻辑层
 * @author: zhaoshuai
 * @date: 2025/2/27 14:35
 */
public interface CouponTemplateRemindService extends IService<CouponTemplateRemindDO> {

    /**
     * 创建抢券预约提醒
     *
     * @param requestParam
     */
    void createCouponRemind(CouponTemplateRemindCreateReqDTO requestParam);
}
