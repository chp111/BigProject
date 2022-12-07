package com.cp.company.controller;

import com.cp.company.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/12/7 23:49
 */
@RestController
@RequestMapping("/output")
public class SendMessageController {

    @Autowired
    private IMessageProvider iMessageProvider;

    @GetMapping(value = "/sendMsg")
    public String sendMsg() {
        return iMessageProvider.send();
    }
}
