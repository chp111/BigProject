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
@EnableDiscoveryClient // 开启服务发现
public class PaymentMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class, args);
    }
}
