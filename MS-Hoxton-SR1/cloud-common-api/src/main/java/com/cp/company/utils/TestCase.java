package com.cp.company.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/12/8 1:10
 */
public class TestCase {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list = list.stream().filter(t -> Integer.valueOf(t) > 1).collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }
}
