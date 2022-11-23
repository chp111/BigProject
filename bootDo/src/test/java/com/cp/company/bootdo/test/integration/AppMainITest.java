package com.cp.company.bootdo.test.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/23 17:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppMainITest {

    @Test
    public void startup() {
        System.out.println("启动类--集成测试");
    }
}
