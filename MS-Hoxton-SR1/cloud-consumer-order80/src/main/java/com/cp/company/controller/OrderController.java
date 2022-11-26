package com.cp.company.controller;

import com.cp.company.controller.vo.PaymentReqVO;
import com.cp.company.utils.CommResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    public static final String URL = "http://CLOULD-PAYMENT-SERVICE";

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
     * 调用查询支付信息服务
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/queryDetailV2")
    public CommResult queryDetailV2(@RequestBody PaymentReqVO reqVO) {
        ResponseEntity<CommResult> entity =
                restTemplate.postForEntity(URL + "/payment/queryDetail", reqVO, CommResult.class);
        log.info("返回结果:{}", entity);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommResult(444, "调用失败");
    }
}

