package com.cp.company.gatewayfilter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/28 23:30
 */
//@Component // 注入为bean
@Slf4j
public class GlobalPaymentFilter implements GlobalFilter, Ordered {

    /**
     * 过滤逻辑
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*********** com in GlobalPaymentFilter **********");
        String userName = exchange.getRequest().getQueryParams().getFirst("username");
        if(StringUtils.isBlank(userName)){
            log.info("*********** GlobalPaymentFilter: 用户名不能为空 **********");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            exchange.getResponse().setComplete();
            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }

    /**
     * 序号越小,优先级越高
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
