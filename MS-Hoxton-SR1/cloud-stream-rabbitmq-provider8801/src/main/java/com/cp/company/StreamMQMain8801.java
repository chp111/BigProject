package com.cp.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/12/7 23:39
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMQMain8801 {

    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class, args);
    }
}
