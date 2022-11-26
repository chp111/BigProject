package com.cp.customrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陈朋
 * @description 自定义Ribbon负载均衡策略
 * @datetime 2022/11/26 21:37
 */
@Configuration
public class MySelfRule {

    // 默认为轮询
    @Bean
    public IRule myRule(){
        // 改为随机
        return new RandomRule();
    }
}
