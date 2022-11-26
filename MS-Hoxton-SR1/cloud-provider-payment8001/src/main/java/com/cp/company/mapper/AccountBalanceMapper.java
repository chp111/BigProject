package com.cp.company.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cp.company.pojo.AccountBalance;
import org.springframework.stereotype.Repository;

/**
 * @author chp
 * @description 针对表【account_balance(账户余额信息表)】的数据库操作Mapper
 * @createDate 2022-11-26 13:57:06
 * @Entity com.cp.company.pojo.AccountBalance
 */
@Repository
public interface AccountBalanceMapper extends BaseMapper<AccountBalance> {

}




