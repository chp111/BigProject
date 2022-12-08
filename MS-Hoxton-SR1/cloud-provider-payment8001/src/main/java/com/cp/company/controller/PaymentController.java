package com.cp.company.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cp.company.controller.vo.PaymentReqVO;
import com.cp.company.pojo.Payment;
import com.cp.company.service.PaymentService;
import com.cp.company.utils.CommResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 支付成功返回支付订单号
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/create")
    public CommResult create(@RequestBody PaymentReqVO reqVO) {
        Payment payment = new Payment();
        payment.setOrderNo(reqVO.getOrderNo());
        boolean flag = paymentService.save(payment);
        if (!flag) {
            return new CommResult(444, "支付失败!");
        }
        return new CommResult(200, "支付成功!", reqVO.getOrderNo());
    }

    /**
     * 根据支付编号查询一笔支付信息
     *
     * @param reqVO
     * @return
     */
    @PostMapping("/queryDetail")
    public CommResult queryDetail(@RequestBody PaymentReqVO reqVO) {
        LambdaQueryWrapper<Payment> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Payment::getOrderNo, reqVO.getOrderNo());
        Payment payment = paymentService.getOne(queryWrapper);
        return new CommResult(200, "查询成功!", payment);
    }

    /**
     * zipkin使用演示示例`
     */
    @GetMapping("/zipkin")
    public String paymentZipkin(String s) {
        return "zipkin 演示示例返回成功";
    }
}
