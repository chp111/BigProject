package test.unit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

/**
 * @author 陈朋
 * @description 分布式锁单元测试 排他锁示例
 * <p>
 * ERROR org.apache.zookeeper.ClientCnxn: Error while calling watcher错误：
 * zooKeeper 默认同一个ip地址连接到服务器的连接数最大是60，如果大于这个数会出现以上错误。解决方法：
 * 设置参数maxClientCnxns=0(即无限制)但是为了防止dos网络攻击还请根据实际情况设置。
 * <p>
 * 此分布式锁使用中会报错：
 * 最终排查分析愿因, jar包版本问题, 排除curator-recipes自身所带的curator-framework,重新引入curator-framework依赖,
 * 并且版本请保持一致
 * @datetime 2022/11/26 16:02
 */
public class DistributedLockUTest {
    public static final String ZK_ADDRESS = "192.168.2.201:2181,192.168.2.202:2181,192.168.2.203:2181";
    public static final String ZK_LOCK_PATH = "/zkLock";
    static CuratorFramework client = null;

    static {
        // 最多重试10次,每次最多等待5秒
        client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS, new RetryNTimes(10, 5000));
        client.start();
    }

    /**
     * 模拟分布式锁(可重入排他锁)
     */
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                testDo();
            }
        }, "线程T1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testDo();
            }
        }, "线程T2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                testDo();
            }
        }, "线程T3").start();

    }

    public static void testDo() {
        InterProcessMutex lock = new InterProcessMutex(client, ZK_LOCK_PATH);
        try {
            // 等待30秒获取锁
            if (lock.acquire(3000, TimeUnit.SECONDS)) {
                System.out.println("====" + Thread.currentThread().getName() + "=== 抢到锁了!");

                // 假设执行业务逻辑
                Thread.sleep(5000);
                System.out.println("====" + Thread.currentThread().getName() + "=== 任务执行完毕!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("====" + Thread.currentThread().getName() + "=== 任务执行异常!");
        } finally {
            try {
                lock.release();
                System.out.println("====" + Thread.currentThread().getName() + "=== 释放锁成功!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("====" + Thread.currentThread().getName() + "=== 释放锁异常!");
            }
        }
    }
}
