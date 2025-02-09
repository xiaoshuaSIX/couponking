package com.xiaoshuai66.couponking.merchant.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: SwaggerConfiguration
 * Package: com.xiaoshuai66.couponking.merchant.admin.config
 * Description:
 *
 * @Author 赵帅
 * @Create 2025/2/9 21:32
 * @Version 1.0
 */
@Slf4j
@Configuration
public class SwaggerConfiguration implements ApplicationRunner {
    @Value("${server.port:8080}")
    private String serverPort;
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("券王-商家后台管理系统")
                        .description("创建优惠券、店家查看以及管理优惠券、创建优惠券发放批次等")
                        .version("v1.0.0")
                        // 设置 OpenAPI 文档的联系信息，包括联系人姓名，邮箱
                        .contact(new Contact().name("xiaoshuai66").email("xs2357377358@163.com"))
                        .license(new License().name("小帅66网络科技有限公司").url("https://github.com/xiaoshuaSIX/couponking"))
                );

    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("API Document: http://127.0.0.1:{}{}/doc.html", serverPort, contextPath);
    }
}
