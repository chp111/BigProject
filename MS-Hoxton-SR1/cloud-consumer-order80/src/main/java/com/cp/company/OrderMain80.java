package com.cp.company;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/25 10:04
 */
@SpringBootApplication
@MapperScan("com.cp.company.mapper")
@EnableDiscoveryClient //开启服务注册与发现
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
