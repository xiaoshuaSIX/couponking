package com.xiaoshuai66.couponking.merchant.admin;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: ${NAME}
 * Package: com.xiaoshuai66.couponking
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/8 16:25
 * @Version 1.0
 */
@SpringBootApplication
@EnableLogRecord(tenant = "MerchantAdmin")
@MapperScan("com.xiaoshuai66.couponking.merchant.admin.dao.mapper")
public class MerchantAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MerchantAdminApplication.class, args);
    }
}