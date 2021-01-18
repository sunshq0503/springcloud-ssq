package com.ssq.order.feign;

import com.ssq.commons.entity.Product;
import com.ssq.commons.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product")
public interface ProductFeign {
    @RequestMapping(value = "/product/test",method = RequestMethod.GET)
    Result test();

    @RequestMapping(value = "/product/selectByProductId/{productId}",method = RequestMethod.GET)
    Product SelectByProductId(@PathVariable("productId") Integer productId);



}
