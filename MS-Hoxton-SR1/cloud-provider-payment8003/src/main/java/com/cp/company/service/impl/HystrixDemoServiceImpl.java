package com.cp.company.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cp.company.service.HystrixDemoServcie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/27 20:55
 */
@Service
@Slf4j
public class HystrixDemoServiceImpl implements HystrixDemoServcie {

    @Value("${server.port}")
    private String serverPort;


    /********** 服务降级 开始 *************/
    @Override
    public String getInfoOk(String userName) {
        int a = 1 / 0;
        return "您好: " + userName + ",服务端口: " + serverPort + "正常!";
    }

    /**
     * 模拟如下业务3秒以内处理完成,服务正常;
     * 但是超过3秒或者程序内抛出异常 则进行服务降级
     *
     * @param userName
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "getInfoFailHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String getInfoFail(String userName) {
        // 服务超时
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 程序运行报错
//        int a = 10 / 0;
        return "您好: " + userName + ",服务端口: " + serverPort + "异常!";
    }

    // 服务降级处理方法
    public String getInfoFailHandler(String userName) {
        return "您好: " + userName + ",服务端口: " + serverPort + "服务降级处理,请稍后重试!";
    }

    /********** 服务降级 结束 *************/


    /********** 服务熔断 开始 *************/

    // 在10秒窗口期内,统计最近10次请求的失败率,若失败率达到60,则触发开启断路器
    @Override
    @HystrixCommand(fallbackMethod = "getInfoV2Fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期,单位:毫秒
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
    })
    public String getCircuitBreaker(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new RuntimeException("用户名不能为空");
        }
        String uuid = IdUtil.simpleUUID();
        return "您好: " + userName + ",UUID: " + uuid + ",服务端口: " + serverPort + "服务正常处理!";
    }

    public String getInfoV2Fallback(String userName) {
        return "服务端口: " + serverPort + "服务熔断触发,服务降级处理!";
    }

    /********** 服务熔断 结束 *************/

}
