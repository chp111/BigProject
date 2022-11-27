package com.cp.company.fallback;

import com.cp.company.feign.HystrixDemoV2Feign;
import org.springframework.stereotype.Service;

/**
 * @author 陈朋
 * @description 支付服务全局服务降级处理
 * @datetime 2022/11/27 23:15
 */
@Service
public class GlobalPaymentFallbackFactory implements HystrixDemoV2Feign {

    @Override
    public String getInfoOk(String userName) {
        return "服务消费端==全局服务降级处理==getInfoOk";
    }

    @Override
    public String getInfoFail(String userName) {
        return "服务消费端==全局服务降级处理==getInfoFail";
    }

    @Override
    public String getInfoV2(String userName) {
        return "服务消费端==全局服务降级处理==getInfoV2";
    }
}
