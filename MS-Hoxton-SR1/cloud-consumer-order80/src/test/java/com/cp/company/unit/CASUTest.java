package com.cp.company.unit;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 陈朋
 * @description CAS 自旋锁 测试
 * @datetime 2022/11/27 11:32
 */
public class CASUTest {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void test01() {
        for (int i = 0; i < 10; i++) {
            doThings(atomicInteger);
        }
    }


    private void doThings(AtomicInteger atomicInteger) {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current > Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));

        System.out.println("自选完毕: 当前是第" + next + "次自旋");
    }
}
