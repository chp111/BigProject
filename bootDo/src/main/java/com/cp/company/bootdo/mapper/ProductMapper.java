package com.cp.company.bootdo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cp.company.bootdo.pojo.Product;
import org.springframework.stereotype.Repository;

/**
 * @author chp
 * @description 针对表【product(商品信息表)】的数据库操作Mapper
 * @createDate 2022-11-24 16:03:32
 * @Entity com.cp.company.bootdo.pojo.Product
 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {

}




