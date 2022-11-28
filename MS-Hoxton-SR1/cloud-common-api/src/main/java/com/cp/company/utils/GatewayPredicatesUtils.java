package com.cp.company.utils;

import java.time.ZonedDateTime;

/**
 * @author 陈朋
 * @description Gateway 断言工具类
 * @datetime 2022/11/28 22:30
 */
public class GatewayPredicatesUtils {

    /**
     * 按时间匹配,生成指定格式的时间
     * - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
     * - Before=2017-01-20T17:42:47.789-07:00[America/Denver]
     * - After=2017-01-20T17:42:47.789-07:00[America/Denver]
     */
    public static ZonedDateTime getPredicatesTime() {
        ZonedDateTime zbj = ZonedDateTime.now();
        return zbj;
    }

}
