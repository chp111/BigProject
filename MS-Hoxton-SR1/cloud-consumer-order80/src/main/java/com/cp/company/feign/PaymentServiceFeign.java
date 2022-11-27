package com.cp.company.feign;

import com.cp.company.controller.vo.PaymentReqVO;
import com.cp.company.utils.CommResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/27 12:24
 */
@FeignClient(value = "${provider.name.payment}", path = "/payment")
public interface PaymentServiceFeign {

    /**
     * 支付成功返回支付订单号
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/create")
    CommResult create(@RequestBody PaymentReqVO reqVO);

    /**
     * 根据支付编号查询一笔支付信息
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/queryDetail")
    CommResult queryDetail(@RequestBody PaymentReqVO reqVO);

    /**
     * 获取服务注册信息
     */
    @GetMapping(value = "/getServiceInfo")
    Object getServiceInfo();
}
