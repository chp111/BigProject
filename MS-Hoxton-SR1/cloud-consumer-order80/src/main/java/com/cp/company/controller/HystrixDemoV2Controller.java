package com.cp.company.controller;

import com.cp.company.feign.HystrixDemoV2Feign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈朋
 * @description 以Feign接口划分服务降级
 * @datetime 2022/11/27 21:02
 */
@RestController
@RequestMapping("/consumer/hystrixDemoV2")
@Slf4j
public class HystrixDemoV2Controller {

    @Autowired
    private HystrixDemoV2Feign hystrixDemoV2Feign;

    @GetMapping("/getInfoOK")
    public String getInfoOK(@RequestParam String userName) {
        return hystrixDemoV2Feign.getInfoOk(userName);
    }

    /**
     * 消费端模拟服务降级
     * 消费最多等待1.5s,而服务提供者需要耗时3s
     *
     * @param userName
     * @return
     */
    @GetMapping("/getInfoFail")
    public String getInfoFail(@RequestParam String userName) {

        return hystrixDemoV2Feign.getInfoFail(userName);
    }

    // 模拟断路器
    @GetMapping("/getInfoV2")
    public String getInfoV2(@RequestParam String userName) {
        return hystrixDemoV2Feign.getInfoV2(userName);
    }
}
