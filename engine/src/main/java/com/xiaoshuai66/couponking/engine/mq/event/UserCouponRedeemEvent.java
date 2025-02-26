package com.xiaoshuai66.couponking.engine.mq.event;

import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateQueryReqDTO;
import com.xiaoshuai66.couponking.engine.dto.req.CouponTemplateRedeemReqDTO;
import com.xiaoshuai66.couponking.engine.dto.resp.CouponTemplateQueryRespDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UserCouponRedeemEvent
 * Package: com.xiaoshuai66.couponking.engine.mq.event
 * Description: 用户兑换优惠券事件
 *
 * @Author 赵帅
 * @Create 2025/2/26 21:32
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponRedeemEvent {

    /**
     * Web 请求参数
     */
    private CouponTemplateRedeemReqDTO requestParam;

    /**
     * 领取次数
     */
    private Integer receiveCount;

    /**
     * 优惠券模版
     */
    private CouponTemplateQueryRespDTO couponTemplate;

    /**
     * 用户ID
     */
    private String userId;
}
