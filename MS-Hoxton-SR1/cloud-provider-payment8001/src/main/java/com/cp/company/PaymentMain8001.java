package com.cp.company;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/24 20:24
 */
@SpringBootApplication
@MapperScan("com.cp.company.mapper")
/**
 * 注解 @EnableDiscoveryClient和注解 @EnableEurekaClient
 * 共同点: 都是能够让注册中心能够发现,扫描到改服务;
 * 不同点: @EnableEurekaClient只适用于Eureka作为注册中心, @EnableDiscoveryClient 可以是其他注册中心.
 */
@EnableDiscoveryClient
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
