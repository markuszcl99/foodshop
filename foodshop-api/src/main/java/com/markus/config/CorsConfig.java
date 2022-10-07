package com.markus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author: markus
 * @date: 2022/10/7 10:01 PM
 * @Description: 跨域配置
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Configuration
public class CorsConfig {
    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter() {
        // 1. 添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 设置服务器允许接收的域，这里不要设置为"*"，对服务器非常危险
        config.addAllowedOrigin("http://localhost:8080");
        config.setAllowCredentials(true);
        // 设置允许的header
        config.addAllowedHeader("*");
        // 设置允许的请求方式
        config.addAllowedMethod("*");
        // 2. 为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);
        // 3. 返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
