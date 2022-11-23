package com.cp.company.bootdo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/23 13:37
 */
@Data
// 设置实体类对应的表名
@TableName("sys_user")
public class SysUserPoJo {

    // 将属性对应的字段指定为表的主键
    // @TableId注解的value属性用于指定主键的字段
    // @TableId注解的type属性用于指定主键的生成策略 默认是雪花ID
    // 主键生成策略: auto-数据库自增ID;
    // assign_id-雪花ID;
    // assign_uuid-分配UUID,String类型;
    // input-用户输入ID;
    // none-未设置主键生成策略,默认为雪花ID,若设置了全局配置,则跟随全局配置
    @TableId
    private Long id;

    // @TableField注解用于指定属性对应的字段名
    @TableField("name")
    private String name;

    private Integer age;

    private String email;
}
