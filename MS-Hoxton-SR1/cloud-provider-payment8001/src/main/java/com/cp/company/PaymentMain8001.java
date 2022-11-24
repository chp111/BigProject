package com.cp.company;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/24 20:24
 */
@SpringBootApplication
@MapperScan("com.cp.company.mapper")
public class PaymentMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
