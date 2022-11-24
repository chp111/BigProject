package com.cp.company.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cp.company.pojo.Payment;
import com.cp.company.service.PaymentService;
import com.cp.company.utils.CommResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/24 20:56
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public CommResult create() {
        Payment payment = new Payment();
        String orderNo = UUID.randomUUID().toString().replace("-", "");
        payment.setOrderNo(orderNo);
        boolean flag = paymentService.save(payment);
        if (!flag) {
            return new CommResult(444, "支付失败!");
        }
        return new CommResult(200, "支付成功!", orderNo);
    }

    @PostMapping("/queryDetail")
    public CommResult queryDetail(@RequestBody Payment paymentVO) {
        LambdaQueryWrapper<Payment> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Payment::getOrderNo, paymentVO.getOrderNo());
        Payment payment = paymentService.getOne(queryWrapper);
        return new CommResult(200, "查询成功!", payment);
    }
}
