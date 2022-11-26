package com.cp.company.controller;

import com.cp.company.controller.vo.PaymentReqVO;
import com.cp.company.utils.CommResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/25 10:25
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 服务注册名严格区分大小写
     * 根据经验, Eureka上面是大写,而zookeeper上面服务注册名称是小写
     */
    public static final String URL = "http://clould-payment-service";

    @Autowired
    private RestTemplate restTemplate;

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
        return restTemplate.postForObject(URL + "/payment/create", payment, CommResult.class);
    }


    /**
     * 调用查询支付信息服务
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/queryDetail")
    public CommResult queryDetail(@RequestBody PaymentReqVO reqVO) {
        return restTemplate.postForObject(URL + "/payment/queryDetail", reqVO, CommResult.class);
    }

    /**
     * 扣款
     */
    @GetMapping("/subtractBalance")
    public CommResult subtractBalance(@RequestParam String userName) {
        return restTemplate.getForObject(URL + "/accBalance/subtractMoney?userName=" + userName, CommResult.class);
    }


}

