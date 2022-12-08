package com.cp.company.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/12/8 16:29
 */
@RestController
@RequestMapping("/config")
@RefreshScope // 开启nacos配置刷新
public class NacosConfigClientController {

    @Value("${custom.config.info}")
    private String customConfigInfo;

    @GetMapping(value = "getInfo")
    public String getInfo(){
        return customConfigInfo;
    }

}
