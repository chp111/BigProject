package com.cp.company.unit;

import com.cp.company.utils.GatewayPredicatesUtils;
import org.junit.Test;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/28 22:38
 */
public class GatewayPredicatesUTest {

    /**
     * 输出结果值举例:
     * 2022-11-28T22:38:52.996+08:00[Asia/Shanghai]
     */
    @Test
    public void test01(){
        System.out.println(GatewayPredicatesUtils.getPredicatesTime());
    }
}
