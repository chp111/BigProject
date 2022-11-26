package com.cp.company.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 账户余额信息表
 * @TableName account_balance
 */
@TableName(value ="account_balance")
@Data
public class AccountBalance implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 账户余额唯一标识
     */
    private String balanceId;

    /**
     * 账户余额
     */
    private String balance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}