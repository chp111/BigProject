package com.cp.company.bootdo.test.integration.impl;

import com.cp.company.bootdo.enums.SexEnum;
import com.cp.company.bootdo.pojo.SysUser;
import com.cp.company.bootdo.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/23 17:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserServiceITest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testCount() {
        // 查询总记录数
        long count = sysUserService.count();
        // 总记录数
        System.out.println("总记录数=" + count);
    }

    @Test
    public void testSaveBatch() {
        // 测试mybatis-plus 批量添加功能, 循环执行单笔添加方法
        List<SysUser> list = new ArrayList<>();
        SysUser sysUser = null;
        for (int i = 1; i <= 10; i++) {
            sysUser = new SysUser();
            sysUser.setUserName("张飞" + i);
            sysUser.setAge(20 + i);
            sysUser.setEmail("zhangfei" + i + "@qq.com");
            if (i % 2 == 0) {
                sysUser.setSex(SexEnum.MALE);
            } else {
                sysUser.setSex(SexEnum.FEMALE);
            }
            list.add(sysUser);
        }
        boolean result = sysUserService.saveBatch(list);
        System.out.println("批量添加结果=" + result);
    }
}
