package com.cp.company.controller;

import com.cp.company.feign.HystrixDemoFeign;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/27 21:02
 */
@RestController
@RequestMapping("/consumer/hystrixDemo")
@Slf4j
// 加@HystrixCommand注解的方法,未注明服务降级处理方法默认走globalConsumerPaymentFallbackMethod服务降级处理方法
@DefaultProperties(defaultFallback = "globalConsumerPaymentFallbackMethod")
public class HystrixDemoController {

    @Autowired
    private HystrixDemoFeign hystrixDemoFeign;

    @GetMapping("/getInfoOK")
    @HystrixCommand
    public String getInfoOK(@RequestParam String userName) {
        return hystrixDemoFeign.getInfoOk(userName);
    }

    /**
     * 消费端模拟服务降级
     * 消费最多等待1.5s,而服务提供者需要耗时3s
     *
     * @param userName
     * @return
     */
    @GetMapping("/getInfoFail")
    @HystrixCommand(fallbackMethod = "getInfoFailHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public String getInfoFail(@RequestParam String userName) {

        return hystrixDemoFeign.getInfoFail(userName);
    }

    public String getInfoFailHandler(@RequestParam String userName) {
        return "您好: " + userName + ",服务消费端个性化服务降级处理,请稍后重试!";
    }

    public String globalConsumerPaymentFallbackMethod() {
        return "服务消费端自行全局服务降级处理,请稍后重试!";
    }
}
