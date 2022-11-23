package com.cp.company.bootdo.test.integration.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cp.company.bootdo.mapper.SysUserMapper;
import com.cp.company.bootdo.pojo.SysUserPoJo;
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
        List<SysUserPoJo> list = sysUserMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        // mybatis-plus 对id默认采用的是雪花id算法生成的唯一主键
        SysUserPoJo sysUser = new SysUserPoJo();
        sysUser.setName("张三");
        sysUser.setAge(20);
        sysUser.setEmail("test_zhangsan@qq.com");
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
        SysUserPoJo sysUser = new SysUserPoJo();
        sysUser.setId(4l);
        sysUser.setName("李四");
        sysUser.setEmail("lisi@qq.com");
        int result = sysUserMapper.updateById(sysUser);
        System.out.println("修改了" + result + "条记录");
    }

    @Test
    public void testSelectById() {
        SysUserPoJo sysUser = sysUserMapper.selectById(1L);
        System.out.println("查询出的用户信息为:" + sysUser);
    }

    @Test
    public void testSelectByBatchIds() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<SysUserPoJo> list = sysUserMapper.selectBatchIds(ids);
        list.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "Jack");
        param.put("age", "20");
        List<SysUserPoJo> list = sysUserMapper.selectByMap(param);
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
        QueryWrapper<SysUserPoJo> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("email")
                .like("name", "a")
                .between("age", 20, 25);
        List<SysUserPoJo> list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        QueryWrapper<SysUserPoJo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id")
                .orderByDesc("age");
        List<SysUserPoJo> list = sysUserMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03() {
        QueryWrapper<SysUserPoJo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1L);
        int result = sysUserMapper.delete(queryWrapper);
        System.out.println("删除了" + result + "条记录");
    }

    @Test
    public void test04(){
        // 将名称包含张飞或者年龄大于20岁的信息,名称修改为李白,邮箱修改为LiBai@qq.com
        QueryWrapper<SysUserPoJo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","张飞")
                .or()
                .gt("age",25);

        SysUserPoJo sysUser = new SysUserPoJo();
        sysUser.setName("李白");
        sysUser.setEmail("LiBai@qq.com");
        int result = sysUserMapper.update(sysUser, queryWrapper);
        System.out.println("修改" + result + "条记录");
    }

}
