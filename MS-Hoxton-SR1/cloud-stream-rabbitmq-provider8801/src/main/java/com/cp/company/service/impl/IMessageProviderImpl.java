package com.cp.company.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cp.company.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

/**
 * @author 陈朋
 * @description 发送消息的实现类
 * @datetime 2022/12/7 23:41
 */
@EnableBinding(Source.class) // 定义消息推送的管道
@Slf4j
public class IMessageProviderImpl implements IMessageProvider {

    /**
     * 消息发送管道
     */
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String serial = IdUtil.simpleUUID();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("serial:{}", serial);
        return serial;
    }
}
