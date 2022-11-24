package com.cp.company.bootdo.test.integration.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cp.company.bootdo.mapper.SysUserMapper;
import com.cp.company.bootdo.pojo.SysUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/23 17:11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserMapperV2ITest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void test01() {
        // 模拟开发场景下组装条件查询
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 25;
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), SysUser::getUserName, name)
                .gt(ObjectUtils.isNotEmpty(ageBegin), SysUser::getAge, ageBegin)
                .lt(ObjectUtils.isNotEmpty(ageEnd), SysUser::getAge, ageEnd);
        List<SysUser> list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        // 模拟开发场景下组装条件更新
        String name = "李白";
        Integer ageBegin = 20;
        Integer ageEnd = 25;
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(StringUtils.isNotBlank(name), SysUser::getUserName, name)
                .gt(ObjectUtils.isNotEmpty(ageBegin), SysUser::getAge, ageBegin)
                .lt(ObjectUtils.isNotEmpty(ageEnd), SysUser::getAge, ageEnd);
        updateWrapper.set(SysUser::getUserName, "张飞")
                .set(SysUser::getEmail, "zf@qq.com");
        int result = sysUserMapper.update(null, updateWrapper);
        System.out.println("更新了" + result + "条记录");
    }

    // 以下写法虽然简单,但是整洁性有待考虑,暂不推荐
    @Test
    public void test03() {
        List<SysUser> list = sysUserMapper.selectList(
                Wrappers.<SysUser>lambdaQuery()
                        .like(SysUser::getUserName, "李白")
                        .and(t -> t.between(SysUser::getAge, 20, 25)
                                .or()
                                .isNotNull(SysUser::getEmail)));
        list.forEach(System.out::println);
    }

    // 集成PageHelper分页插件
    @Test
    public void tes04(){
        PageHelper.startPage(1, 10);
        List<SysUser> list = sysUserMapper.selectList(null);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        pageInfo.getList().forEach(System.out::println);
    }
}
