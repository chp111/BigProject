package com.cp.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/28 0:10
 */
@SpringBootApplication
@EnableEurekaClient
/**
 * http://localhost:8001/hystrix.stream
 */
@EnableHystrixDashboard // 开启Hystrix仪表盘
public class DashBoardMain9001 {

    public static void main(String[] args) {
        SpringApplication.run(DashBoardMain9001.class, args);
    }
}
