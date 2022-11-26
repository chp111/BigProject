package com.cp.company.controller;

import com.cp.company.common.ZookeeperDistributedReentrantExcludeLock;
import com.cp.company.pojo.AccountBalance;
import com.cp.company.service.AccountBalanceService;
import com.cp.company.utils.CommResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.utils.CloseableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/26 17:03
 */
@RestController
@RequestMapping("/accBalance")
@Slf4j
public class AccountBalanceController {

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private ZookeeperDistributedReentrantExcludeLock zookeeperDistributedReentrantExcludeLock;
    /**
     * 分布式可冲入排他锁路径
     */
    private static final String ZK_LOCK_PATH = "/zkReentrantExcludeLock";


    @GetMapping(value = "/subtractMoney")
    public CommResult subtractMoney(String userName) {
        CommResult result = new CommResult(200, userName + "扣款成功!");
        CuratorFramework client = zookeeperDistributedReentrantExcludeLock.createAndStartupZkClient();
        InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
        try {
            // 等待30秒获取锁
            if (lock.acquire(3000, TimeUnit.SECONDS)) {
                log.info("==========={}===========抢到锁了!", userName);
                // 执行业务逻辑
                boolean flag = this.businessLogic(userName);
                if (!flag) {
                    result.setCode(333);
                    result.setMessage(userName + "扣款失败");
                }
                log.info("==========={}===========任务执行完毕!", userName);
            }
        } catch (Exception e) {
            log.error("==========={}===========任务执行异常,原因:", userName, e);
        } finally {
            try {
                lock.release();
                log.info("==========={}===========释放锁成功!", userName);
            } catch (Exception e) {
                log.error("==========={}===========释放锁异常,原因:", userName, e);
            }
        }
        return result;
    }

    /**
     * 扣款核心业务逻辑
     *
     * @param userName
     */
    private boolean businessLogic(String userName) {
        AccountBalance accountBalance1 = accountBalanceService.getById(1L);
        BigDecimal oldBalance1 = new BigDecimal(accountBalance1.getBalance());
        BigDecimal subtractBalance1 = new BigDecimal(300);
        BigDecimal leaveBalance1 = oldBalance1.subtract(subtractBalance1);
        if (oldBalance1.compareTo(BigDecimal.ZERO) >= 0
                && leaveBalance1.compareTo(BigDecimal.ZERO) >= 0) {
            // 扣款
            accountBalance1.setBalance(leaveBalance1.toString());
            accountBalanceService.updateById(accountBalance1);
            log.info("==========={}===========扣款成功!", userName);
            return true;
        }
        log.info("==========={}===========扣款失败,余额不足!", userName);
        return false;
    }
}
