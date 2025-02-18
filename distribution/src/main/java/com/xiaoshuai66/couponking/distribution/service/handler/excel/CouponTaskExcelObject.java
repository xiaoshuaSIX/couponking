package com.xiaoshuai66.couponking.distribution.service.handler.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author 赵帅
 * @ClassName: CouponTaskExcelObject.java
 * @Description: 优惠券推送任务 Excel 元数据实体
 * @Create 2025/2/18 14:46
 */
@Data
public class CouponTaskExcelObject {

    @ExcelProperty("用户ID")
    private String userId;

    @ExcelProperty("手机号")
    private String phone;

    @ExcelProperty("邮箱")
    private String mail;
}
