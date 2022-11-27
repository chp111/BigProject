package com.cp.company.feign;

import com.cp.company.fallback.GlobalPaymentFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${provider.name.payment}", path = "/hystrixDemo",
        fallback = GlobalPaymentFallbackFactory.class)
public interface HystrixDemoV2Feign {

    @GetMapping("/getInfoOK")
    String getInfoOk(@RequestParam(value = "userName") String userName);

    @GetMapping("/getInfoFail")
    String getInfoFail(@RequestParam(value = "userName") String userName);

    @GetMapping("/getInfoV2")
    String getInfoV2(@RequestParam(value = "userName") String userName);

}
