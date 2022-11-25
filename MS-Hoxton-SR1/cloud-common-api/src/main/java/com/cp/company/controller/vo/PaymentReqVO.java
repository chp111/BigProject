package com.cp.company.controller.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈朋
 * @description 支付接口上送对象
 * @datetime 2022/11/25 10:36
 */
@Data
public class PaymentReqVO implements Serializable {

    private static final long serialVersionUID = 8409071289531081331L;
    /**
     * 订单支付编号
     */
    private String orderNo;
}
