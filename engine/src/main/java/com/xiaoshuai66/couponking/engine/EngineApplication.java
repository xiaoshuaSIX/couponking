package com.xiaoshuai66.couponking.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: ${NAME}
 * Package: com.xiaoshuai66.couponking
 * Description: 引擎服务 | 负责优惠券单个查看、列表查看、锁定以及核销等功能
 *
 * @Author 赵帅
 * @Create 2025/2/17 9:32
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.xiaoshuai66.couponking.engine.dao.mapper")
public class EngineApplication {
    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }
}