package com.cp.company.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cp.company.controller.vo.PaymentReqVO;
import com.cp.company.pojo.Payment;
import com.cp.company.service.PaymentService;
import com.cp.company.utils.CommResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/24 20:56
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务发现
     */
    @Autowired
    private DiscoveryClient discoveryClient;

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
        String serverPortInfo = "sererPort: " + serverPort;
        if (!flag) {
            return new CommResult(444, serverPortInfo + "支付失败!");
        }
        return new CommResult(200, serverPortInfo + "支付成功!", reqVO.getOrderNo());
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
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String serverPortInfo = "sererPort: " + serverPort;
        return new CommResult(200, serverPortInfo + "查询成功!", payment);
    }

    /**
     * 获取服务注册信息
     */
    @GetMapping(value = "/getServiceInfo")
    public Object getServiceInfo() {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> services = discoveryClient.getServices();
        List<String> instanceList = new ArrayList<>();
        for (String element : services) {
            log.info("****element: " + element);
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(element);
            for (ServiceInstance instance : serviceInstances) {
                String logstr = "实例ID: " + instance.getInstanceId() + "  "
                        + "实例IP: " + instance.getHost() + "  "
                        + "实例Port: " + instance.getPort() + "  "
                        + "URI: " + instance.getUri();
                log.info(logstr);
                instanceList.add(logstr);
            }
        }
        resultMap.put("services", services);
        resultMap.put("instances", instanceList);
        return resultMap;
    }
}
