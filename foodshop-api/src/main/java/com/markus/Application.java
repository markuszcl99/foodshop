package com.markus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: markus
 * @date: 2022/9/29 10:13 PM
 * @Description: 应用启动类
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@SpringBootApplication
//@SpringBootConfiguration 标识spring容器
//@EnableAutoConfiguration  表示自动装配的类
//@ComponentScan 扫描启动类所在包下的所有Bean
@MapperScan(basePackages = "com.markus.mapper") // 扫描mapper所在的包
@ComponentScan(basePackages = {"com.markus","org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
