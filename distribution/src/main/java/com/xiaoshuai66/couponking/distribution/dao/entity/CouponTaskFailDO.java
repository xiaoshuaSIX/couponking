package com.xiaoshuai66.couponking.distribution.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 赵帅
 * @ClassName: CouponTaskFailDO.java
 * @Description: 优惠券模板失败记录数据库持久层实体
 * @Create 2025/2/20 11:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("t_coupon_task_fail")
public class CouponTaskFailDO {

    /**
     * id
     */
    private Long id;

    /**
     * 批量id
     */
    private Long batchId;

    /**
     * JSON字符串，存储失败原因，Excel 行数等信息
     */
    @TableField(value = "`json_object`")
    private String jsonObject;
}
