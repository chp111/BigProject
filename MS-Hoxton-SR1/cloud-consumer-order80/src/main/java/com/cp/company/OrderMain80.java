package com.cp.company;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/25 10:04
 */
@SpringBootApplication
@MapperScan("com.cp.company.mapper")
// 开启OpenFeign
@EnableFeignClients
@EnableEurekaClient
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
