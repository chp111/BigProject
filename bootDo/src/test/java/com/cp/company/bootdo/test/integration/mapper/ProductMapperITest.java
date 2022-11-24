package com.cp.company.bootdo.test.integration.mapper;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;

import com.cp.company.bootdo.enums.SexEnum;
import com.cp.company.bootdo.mapper.ProductMapper;
import com.cp.company.bootdo.pojo.Product;
import com.cp.company.bootdo.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/23 15:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperITest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testSelectList() {
        // 通过条件构造器查询一个list集合,若没有条件,则可以设置null为参数
        List<Product> list = productMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        // mybatis-plus 对id默认采用的是雪花id算法生成的唯一主键
        Product product = new Product();
        product.setProductName("苹果13Pro");
        product.setProductShortName("apple");
        product.setProductPrice("10000.00");
        product.setProduceDate(LocalDate.now());
        product.setExpireDate(LocalDate.now());
        product.setProductCount(10000L);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        product.setRemarks("备注");
        int rows = productMapper.insert(product);
        System.out.println("插入" + rows + "条记录");
        System.out.println("当前新增id=" + product.getId());
    }
}
