package com.cp.company.bootdo.test.integration.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cp.company.bootdo.enums.SexEnum;
import com.cp.company.bootdo.mapper.SysUserMapper;
import com.cp.company.bootdo.pojo.SysUser;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/23 15:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserMapperITest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void testSelectList() {
        // 通过条件构造器查询一个list集合,若没有条件,则可以设置null为参数
        List<SysUser> list = sysUserMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        // mybatis-plus 对id默认采用的是雪花id算法生成的唯一主键
        SysUser sysUser = new SysUser();
        sysUser.setUserName("张三");
        sysUser.setAge(20);
        sysUser.setEmail("test_zhangsan@qq.com");
        sysUser.setSex(SexEnum.MALE);
        int rows = sysUserMapper.insert(sysUser);
        System.out.println("插入" + rows + "条记录");
        System.out.println("当前新增id=" + sysUser.getId());
    }

    @Test
    public void testDeleteById() {
        // 根据id删除用户信息
        int result = sysUserMapper.deleteById(1595324375061475330L);
        System.out.println("删除" + result + "条记录");
    }

    @Test
    public void testDeleteByMap() {
        // 根据多个条件删除
        Map<String, Object> param = new HashMap<>();
        param.put("name", "张三");
        param.put("age", "23");
        int result = sysUserMapper.deleteByMap(param);
        System.out.println("删除" + result + "条记录");
    }

    @Test
    public void testDeleteByBatchIds() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        int result = sysUserMapper.deleteBatchIds(ids);
        System.out.println("删除" + result + "条记录");
    }


    @Test
    public void testUpdate() {
        SysUser sysUser = new SysUser();
        sysUser.setId(4l);
        sysUser.setUserName("李四");
        sysUser.setEmail("lisi@qq.com");
        int result = sysUserMapper.updateById(sysUser);
        System.out.println("修改了" + result + "条记录");
    }

    @Test
    public void testSelectById() {
        SysUser sysUser = sysUserMapper.selectById(1L);
        System.out.println("查询出的用户信息为:" + sysUser);
    }

    @Test
    public void testSelectByBatchIds() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<SysUser> list = sysUserMapper.selectBatchIds(ids);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "Jack");
        param.put("age", "20");
        List<SysUser> list = sysUserMapper.selectByMap(param);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectMapById() {
        Map<String, Object> resultMap = sysUserMapper.selectMapById(1L);
        System.out.println("查询结果为:" + resultMap);
    }

    // 使用Wrapper进行条件组装查询、更新
    @Test
    public void test01() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("email")
                .like("name", "a")
                .between("age", 20, 25);
        List<SysUser> list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id")
                .orderByDesc("age");
        List<SysUser> list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1L);
        int result = sysUserMapper.delete(queryWrapper);
        System.out.println("删除了" + result + "条记录");
    }

    @Test
    public void test04() {
        // 将名称包含张飞或者年龄大于20岁的信息,名称修改为李白,邮箱修改为LiBai@qq.com
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "张飞")
                .or()
                .gt("age", 25);

        SysUser sysUser = new SysUser();
        sysUser.setUserName("李白");
        sysUser.setEmail("LiBai@qq.com");
        int result = sysUserMapper.update(sysUser, queryWrapper);
        System.out.println("修改" + result + "条记录");
    }

    @Test
    public void test05() {
        // 条件优先级
        // 将名称包含李白且(年龄大于20岁或email不为空)的信息进行修改,名称修改为张飞,邮箱修改为zf@qq.com
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "李白")
                .and(t -> t.gt("age", 20)
                        .or()
                        .isNotNull("email"));
        SysUser sysUser = new SysUser();
        sysUser.setUserName("张飞");
        sysUser.setEmail("zf@qq.com");
        int result = sysUserMapper.update(sysUser, queryWrapper);
        System.out.println("修改" + result + "条记录");
    }

    @Test
    public void test06() {
        // 查询指定字段信息
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        List<Map<String, Object>> mapList = sysUserMapper.selectMaps(queryWrapper);
        mapList.forEach(System.out::println);
    }

    @Test
    public void test07() {
        // 子查询
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from sys_user where id < 10");
        List<SysUser> list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test08() {
        // 通过UpdateWrapper实现更新
        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        // 更新条件
        updateWrapper.like("name", "飞")
                .and(t -> t.gt("age", 20)
                        .or()
                        .isNotNull("email"));
        // 更新值
        updateWrapper.set("name", "李白")
                .set("email", "libai@qq.com");

        // 更推荐使用UpdateWrapper
        int result = sysUserMapper.update(null, updateWrapper);
        System.out.println("修改" + result + "条记录");
    }

    @Test
    public void test09() {
        // 模拟开发中条件组装的情况
        String name = "";
        Integer age = 20;
        String email = "libai";
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (ObjectUtils.isNotEmpty(age)) {
            queryWrapper.gt("age", age);
        }
        if (StringUtils.isNotBlank(email)) {
            queryWrapper.like("email", email);
        }
        List<SysUser> list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test10() {
        // 模拟开发中条件组装的情况 升级版
        String name = "";
        Integer age = 20;
        String email = "libai";
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNoneBlank(name), "name", name)
                .gt(ObjectUtils.isNotEmpty(age), "age", age)
                .like(StringUtils.isNotBlank(email), "email", email);
        List<SysUser> list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
