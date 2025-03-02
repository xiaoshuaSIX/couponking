package com.xiaoshuai66.couponking.engine.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: CouponSettlementDO
 * Package: com.xiaoshuai66.couponking.engine.dao.entity
 * Description: 优惠券结算实体
 *
 * @Author 赵帅
 * @Create 2025/3/1 11:12
 * @Version 1.0
 */
@Data
@TableName("t_coupon_settlement")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponSettlementDO {

    /**
     * id
     */
    private Long id;

    /**
     * 优惠券id
     */
    private Long orderId;

    /**
     * 用户od
     */
    private Long userId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 结算单状态 0：锁定 1：已取消 2：已支付 3：已退款
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
