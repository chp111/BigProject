package com.cp.company.test.integration;

import com.cp.company.mapper.AccountBalanceMapper;
import com.cp.company.pojo.AccountBalance;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/26 14:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountBalanceMapperITest {

    @Autowired
    private AccountBalanceMapper accountBalanceMapper;

    // 未使用分布式锁情况下,扣款不正常
    @Test
    public void test01() {
        AccountBalance accountBalance1 = accountBalanceMapper.selectById(1L);
        AccountBalance accountBalance2 = accountBalanceMapper.selectById(1L);
        BigDecimal oldBalance1 = new BigDecimal(accountBalance1.getBalance());
        BigDecimal subtractBalance1 = new BigDecimal(100);
        BigDecimal leaveBalance1 = oldBalance1.subtract(subtractBalance1);
        if (oldBalance1.compareTo(BigDecimal.ZERO) >= 0
                && leaveBalance1.compareTo(BigDecimal.ZERO) >= 0) {
            // 扣款
            accountBalance1.setBalance(leaveBalance1.toString());
            accountBalanceMapper.updateById(accountBalance1);
        } else {
            System.out.println("小李扣款失败");
        }

        BigDecimal oldBalance2 = new BigDecimal(accountBalance2.getBalance());
        BigDecimal subtractBalance2 = new BigDecimal(200);
        BigDecimal leaveBalance2 = oldBalance2.subtract(subtractBalance2);
        if (oldBalance2.compareTo(BigDecimal.ZERO) >= 0
                && leaveBalance2.compareTo(BigDecimal.ZERO) >= 0) {
            // 扣款
            accountBalance2.setBalance(leaveBalance2.toString());
            accountBalanceMapper.updateById(accountBalance2);
        } else {
            System.out.println("小王扣款失败");
        }
    }
}
