package com.cp.company.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 陈朋
 * @description zookeeper 分布式可重入排他锁
 * @datetime 2022/11/26 17:10
 */
@Component
@Slf4j
public class ZookeeperDistributedReentrantExcludeLock {

    /**
     * zookeeper集群信息
     */
    @Value("${spring.cloud.zookeeper.connect-string}")
    private String zk_address;
    /**
     * 客户端连接对象
     */
    private static volatile CuratorFramework client = null;

    /**
     * 连接zk集群
     */
    public CuratorFramework createAndStartupZkClient() {
        if (null == client) {
            log.info("连接zookeeper集群开始: {}", zk_address);
            synchronized (ZookeeperDistributedReentrantExcludeLock.class) {
                if (null == client) {
                    // 最多重试10次,每次最多等待5秒
                    client = CuratorFrameworkFactory.newClient(
                            zk_address, new RetryNTimes(10, 5000));
                    client.start();
                    log.info("连接zookeeper集群成功: {}", zk_address);
                }
            }
        }
        return client;
    }
}
