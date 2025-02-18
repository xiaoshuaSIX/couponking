package com.xiaoshuai66.couponking.settlement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 赵帅
 * @ClassName SettlementApplication
 * @Description 结算服务｜负责用户下单时订单金额计算功能，因和订单相关联，该服务流量较大
 * @Create 2025/2/18 9:55
 */
@SpringBootApplication
public class SettlementApplication {
    public static void main(String[] args) {
        SpringApplication.run(SettlementApplication.class, args);
    }
}