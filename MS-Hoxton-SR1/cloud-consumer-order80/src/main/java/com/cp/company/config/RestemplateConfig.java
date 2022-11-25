package com.cp.company.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/25 10:24
 */
@Configuration
public class RestemplateConfig {

    @Bean
    // 注解@LoadBalanced 开启负载均衡
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
