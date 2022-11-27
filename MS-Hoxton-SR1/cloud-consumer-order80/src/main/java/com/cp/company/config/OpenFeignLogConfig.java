package com.cp.company.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈朋
 * @description OpenFeign Log日志增强配置类
 * @datetime 2022/11/27 13:15
 */
@Configuration
public class OpenFeignLogConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        // FULL 最全的日志
        return Logger.Level.FULL;
    }
}
