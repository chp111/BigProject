package com.cp.company;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/24 20:24
 */
@SpringBootApplication
@MapperScan("com.cp.company.mapper")
@EnableEurekaClient
@EnableDiscoveryClient // 开启服务发现
@EnableCircuitBreaker // 开启断路器功能(Hystrix)
public class PaymentMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class, args);
    }

    /**
     * 此配置为了服务监控 Hystrix DashBoard 而配置。
     * 与服务容错本身无关.
     * Spring Cloud 升级的坑, 默认配置路径不是/hystrix.stream,所以需要额外配置。
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
