package com.ssq.commons.feign;

import com.ssq.commons.feign.fallback.ProductFeignFallBack;
import com.ssq.commons.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product",fallbackFactory = ProductFeignFallBack.class)
public interface ProductFeign {
    @RequestMapping(value = "/product/test",method = RequestMethod.GET)
    Result test();

    @RequestMapping(value = "/product/selectByProductId/{productId}",method = RequestMethod.GET)
    Result SelectByProductId(@PathVariable("productId") Integer productId);



}
