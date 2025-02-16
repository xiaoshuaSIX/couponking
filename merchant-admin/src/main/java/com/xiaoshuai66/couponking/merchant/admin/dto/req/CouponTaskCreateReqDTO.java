package com.xiaoshuai66.couponking.merchant.admin.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * ClassName: CouponTaskCreateReqDTO
 * Package: com.xiaoshuai66.couponking.merchant.admin.dto.req
 * Description: 优惠券推送任务创建请求参数实体
 *
 * @Author 赵帅
 * @Create 2025/2/16 00:12
 * @Version 1.0
 */
@Data
public class CouponTaskCreateReqDTO {

    /**
     * 优惠券批次任务名称
     */
    @Schema(description = "优惠券批次任务名称",
            example = "发送百万优惠券推送任务",
            required = true)
    private String taskName;

    /**
     * 文件地址
     */
    @Schema(description = "优惠券批次任务名称",
            // step1 调用 ExcelGenerateTests 里的单元测试用例，生成 Excel 测试示例文件
            // step2 复制项目目录下 tmp/oneCoupon任务推送Excel 的绝对路径，Windows 和 Mac 下有些许区别，Windows 下应该是 D:\Users\xxx\xxx
            example = "/Users/zhaoshuai/Documents/Code/learn_java/couponking/tmp/oneCoupon任务推送Excel.xlsx",
            required = true)
    private String fileAddress;

    /**
     * 通知方式，可组合使用 0：站内信 1:弹框推送 2:邮箱 3:短信
     */
    @Schema(description = "通知方式",
            example = "0,3",
            required = true)
    private String notifyType;

    /**
     * 优惠券模版id
     */
    @Schema(description = "优惠券模版id",
            example = "1891175172434841602",
            required = true)
    private String couponTemplateId;

    /**
     * 发送类型 0：立即发送 1:定时发送
     */
    @Schema(description = "发送类型",
            example = "0",
            required = true)
    private Integer sendType;

    /**
     * 发送时间
     */
    @Schema(description = "发送时间", example = "2025-02-18 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;

}
