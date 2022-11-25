package com.cp.company.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈朋
 * @description 接口返回结果封装类
 * @datetime 2022/11/24 20:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommResult<T> {

    /**
     * 状态码
     * 200 - 交易成功
     * 444 - 交易失败
     */
    private Integer code;
    /**
     * 交易信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    public CommResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
