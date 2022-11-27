package com.cp.company.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.company.mapper.PaymentMapper;
import com.cp.company.pojo.Payment;
import com.cp.company.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chp
 * @description 针对表【payment】的数据库操作Service实现
 * @createDate 2022-11-24 20:53:10
 */
@Service
@Slf4j
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
        implements PaymentService {

}




