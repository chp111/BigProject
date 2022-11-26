package com.cp.company;

import com.cp.customrule.MySelfRule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/25 10:04
 */
@SpringBootApplication
@MapperScan("com.cp.company.mapper")
@EnableEurekaClient
// 重新指定Ribbon负载均衡策略
@RibbonClient(name = "CLOULD-PAYMENT-SERVICE", configuration = {MySelfRule.class})
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
