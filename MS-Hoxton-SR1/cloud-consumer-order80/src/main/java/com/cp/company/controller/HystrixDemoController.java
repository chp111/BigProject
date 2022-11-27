package com.cp.company.controller;

import com.cp.company.feign.HystrixDemoFeign;
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
public class HystrixDemoController {

    @Autowired
    private HystrixDemoFeign hystrixDemoFeign;

    @GetMapping("/getInfoOK")
    public String getInfoOK(@RequestParam String userName) {
        return hystrixDemoFeign.getInfoOk(userName);
    }

    @GetMapping("/getInfoFail")
    public String getInfoFail(@RequestParam String userName) {
        return hystrixDemoFeign.getInfoFail(userName);
    }
}
