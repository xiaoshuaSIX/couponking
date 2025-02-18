package com.xiaoshuai66.couponking.distribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: DistributionApplication
 * Package: com.xiaoshuai66.couponking
 * Description: 分发模块 | 负责批次分发用户优惠券，可以应用弹框推送、站内信或短信通知等
 *
 * @Author 赵帅
 * @Create 2025/2/18 9:36
 * @Version 1.0
 */
@SpringBootApplication
public class DistributionApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributionApplication.class, args);
    }
}