package com.cp.company.controller;

import com.cp.company.service.HystrixDemoServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈朋
 * @description Hystrix 案例
 * @datetime 2022/11/27 20:28
 */
@RestController
@RequestMapping("/hystrixDemo")
public class HystrixDemoContoller {

    @Autowired
    private HystrixDemoServcie hystrixDemoServcie;

    @GetMapping("/getInfoOK")
    public String getInfoOK(@RequestParam String userName) {
        return hystrixDemoServcie.getInfoOk(userName);
    }

    @GetMapping("/getInfoFail")
    public String getInfoFail(@RequestParam String userName) {
        return hystrixDemoServcie.getInfoFail(userName);
    }

    // 服务熔断案例
    @GetMapping("/getInfoV2")
    public String getInfoV2(@RequestParam String userName) {
        return hystrixDemoServcie.getCircuitBreaker(userName);
    }

}
