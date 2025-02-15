package com.xiaoshuai66.couponking.merchant.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai66.couponking.merchant.admin.dao.entity.CouponTaskDO;
import com.xiaoshuai66.couponking.merchant.admin.dto.req.CouponTaskCreateReqDTO;

/**
 * ClassName: CouponTaskService
 * Package: com.xiaoshuai66.couponking.merchant.admin.service
 * Description: 优惠券推送业务逻辑层
 *
 * @Author 赵帅
 * @Create 2025/2/16 00:10
 * @Version 1.0
 */
public interface CouponTaskService extends IService<CouponTaskDO> {

    /**
     * 商家创建优惠券推送任务
     *
     * @param requestParam 请求参数
     */
    void createCouponTask(CouponTaskCreateReqDTO requestParam);
}
