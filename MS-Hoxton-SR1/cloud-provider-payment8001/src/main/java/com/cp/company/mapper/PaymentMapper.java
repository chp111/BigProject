package com.cp.company.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cp.company.pojo.Payment;
import org.springframework.stereotype.Repository;

/**
 * @author chp
 * @description 针对表【payment】的数据库操作Mapper
 * @createDate 2022-11-24 20:53:10
 * @Entity com.cp.company.pojo.Payment
 */
@Repository
public interface PaymentMapper extends BaseMapper<Payment> {

}




