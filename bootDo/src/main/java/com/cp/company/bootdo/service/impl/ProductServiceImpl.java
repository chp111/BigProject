package com.cp.company.bootdo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.company.bootdo.mapper.ProductMapper;
import com.cp.company.bootdo.pojo.Product;
import com.cp.company.bootdo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chp
 * @description 针对表【product(商品信息表)】的数据库操作Service实现
 * @createDate 2022-11-24 16:03:32
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {

}




