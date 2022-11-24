package com.cp.company.bootdo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 性别枚举类
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    MALE(1, "男"),

    FEMALE(2, "女"),

    UNKNOW(3, "未知");

    /**
     * 键
     */
    // @EnumValue注解指定字段的取值
    @EnumValue
    private Integer key;

    /**
     * 值
     */
    private String value;

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return
     */
    public static String getValueByKey(Integer key) {
        for (SexEnum item : values()) {
            if (Objects.equals(item.key, key)) {
                return item.value;
            }
        }
        return null;
    }

    /**
     * 根据键获取枚举类
     *
     * @param key 键
     * @return
     */
    public static SexEnum getEnumByKey(Integer key) {
        for (SexEnum item : values()) {
            if (Objects.equals(item.key, key)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 根据值获取枚举类
     *
     * @param value 值
     * @return
     */
    public static SexEnum getEnumByValue(String value) {
        for (SexEnum item : values()) {
            if (Objects.equals(item.value, value)) {
                return item;
            }
        }
        return null;
    }
}
