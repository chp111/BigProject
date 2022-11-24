package com.cp.company.bootdo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 商品信息表
 *
 * @TableName product
 */
@TableName(value = "product")
@Data
public class Product extends BasePoJo implements Serializable {

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品简称
     */
    private String productShortName;

    /**
     * 商品价格
     */
    private String productPrice;

    /**
     * 生产日期
     */
    private LocalDate produceDate;

    /**
     * 失效日期
     */
    private LocalDate expireDate;

    /**
     * 商品数量
     */
    private Long productCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}