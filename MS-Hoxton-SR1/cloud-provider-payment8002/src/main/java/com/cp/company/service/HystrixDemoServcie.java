package com.cp.company.service;

public interface HystrixDemoServcie {

    String getInfoOk(String userName);

    String getInfoFail(String userName);

    String getCircuitBreaker(String userName);

}
