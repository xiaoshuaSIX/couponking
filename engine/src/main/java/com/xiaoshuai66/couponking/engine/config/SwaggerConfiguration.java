package com.xiaoshuai66.couponking.engine.config;

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
 * Package: com.xiaoshuai66.couponking.engine.config
 * Description: 设置文档 API Swagger 配置信息，为了让 <a href="http://127.0.0.1:{server.port}{server.servlet.context-path}/doc.html" /> 中的信息看着更饱满
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
                        .title("券王-核心引擎系统")
                        .description("负责优惠券单个查看、列表查看、锁定以及核销等功能等")
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
