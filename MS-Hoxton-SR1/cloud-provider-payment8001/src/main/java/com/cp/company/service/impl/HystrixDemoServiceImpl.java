package com.cp.company.service.impl;

import com.cp.company.service.HystrixDemoServcie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public String getInfoOk(String userName) {
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
}
