package com.cp.company.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/24 20:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
