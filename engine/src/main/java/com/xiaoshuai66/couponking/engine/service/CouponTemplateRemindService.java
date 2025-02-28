package com.xiaoshuai66.couponking.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai66.couponking.engine.dao.entity.CouponTemplateRemindDO;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRemindCancelReqDTO;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRemindCreateReqDTO;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRemindQueryReqDTO;
import com.xiaoshuai66.couponking.engine.dto.resp.CouponTemplateRemindQueryRespDTO;
import com.xiaoshuai66.couponking.engine.service.handler.remind.dto.CouponTemplateRemindDTO;

import java.util.List;

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

    /**
     * 取消抢券预约提醒
     *
     * @param requestParam 请求参数
     */
    void cancelCouponRemind(CouponTemplateRemindCancelReqDTO requestParam);

    /**
     * 检查是否取消抢券预约提醒
     *
     * @param requestParam 请求参数
     */
    boolean isCancelRemind(CouponTemplateRemindDTO requestParam);

    /**
     * 分页查询抢券预约提醒
     *
     * @param requestParam 请求参数
     */
    List<CouponTemplateRemindQueryRespDTO> listCouponRemind(CouponTemplateRemindQueryReqDTO requestParam);
}
