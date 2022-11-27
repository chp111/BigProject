package com.cp.company.controller;

import com.cp.company.controller.vo.PaymentReqVO;
import com.cp.company.feign.PaymentServiceFeign;
import com.cp.company.utils.CommResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/25 10:25
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private PaymentServiceFeign paymentServiceFeign;

    /**
     * 调用支付服务
     *
     * @return
     */
    @PostMapping("/create")
    public CommResult create() {
        PaymentReqVO payment = new PaymentReqVO();
        String orderNo = UUID.randomUUID().toString().replace("-", "");
        payment.setOrderNo(orderNo);
        return paymentServiceFeign.create(payment);
    }


    /**
     * 调用查询支付信息服务
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/queryDetail")
    public CommResult queryDetail(@RequestBody PaymentReqVO reqVO) {
        return paymentServiceFeign.queryDetail(reqVO);
    }

    /**
     * 调用查询支付信息服务
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/queryDetailV2")
    public Object queryDetailV2(@RequestBody PaymentReqVO reqVO) {
        return paymentServiceFeign.getServiceInfo();
    }
}

