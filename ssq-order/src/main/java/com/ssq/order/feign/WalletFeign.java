package com.ssq.order.feign;

import com.ssq.commons.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "wallet")
public interface WalletFeign {
    @RequestMapping(value = "/wallet/expenditure/{userId}/{amount}",method = RequestMethod.GET)
    Result expenditure(@PathVariable String userId,@PathVariable Long amount);

    @RequestMapping(value = "/wallet/getBalance/{userId}",method = RequestMethod.GET)
    Long getBalance(@PathVariable String userId);
}
