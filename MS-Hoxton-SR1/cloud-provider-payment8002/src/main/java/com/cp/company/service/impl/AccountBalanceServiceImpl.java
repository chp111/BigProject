package com.cp.company.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.company.mapper.AccountBalanceMapper;
import com.cp.company.pojo.AccountBalance;
import com.cp.company.service.AccountBalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chp
 * @description 针对表【account_balance(账户余额信息表)】的数据库操作Service实现
 * @createDate 2022-11-26 13:57:06
 */
@Service
@Slf4j
public class AccountBalanceServiceImpl extends ServiceImpl<AccountBalanceMapper, AccountBalance>
        implements AccountBalanceService {

}




