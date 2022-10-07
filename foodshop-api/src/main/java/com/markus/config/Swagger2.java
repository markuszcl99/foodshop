package com.markus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: markus
 * @date: 2022/10/7 6:03 PM
 * @Description: 配置类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    // http://localhost:8088/swagger-ui.html 原路径
    // http://localhost:8088/doc.html 新路径
    // 配置Swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) // 指定api类型为 swagger2
                .apiInfo(apiInfo())                             // 用于定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.markus.controller"))  // 指定controller包
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("电商平台接口api")        // 文档页标题
                .contact(new Contact("markus",
                        "https://www.markuszhang.com",
                        "e_markuszhang@163.com"))// 联系人信息
                .description("转为电商系统提供的api文档")
                .version("1.0.1")
                .termsOfServiceUrl("https://www.markuszhang.com")
                .build();
    }
}
