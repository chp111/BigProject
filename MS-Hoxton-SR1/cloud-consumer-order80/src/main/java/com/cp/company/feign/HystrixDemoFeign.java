package com.cp.company.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${provider.name.payment}", path = "/hystrixDemo")
public interface HystrixDemoFeign {

    @GetMapping("/getInfoOK")
    String getInfoOk(@RequestParam(value = "userName") String userName);

    @GetMapping("/getInfoFail")
    String getInfoFail(@RequestParam(value = "userName") String userName);

}
