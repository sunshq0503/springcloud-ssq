package com.ssq.product.controller;

import com.ssq.commons.entity.Product;
import com.ssq.commons.response.Result;
import com.ssq.product.model.InboundRequest;
import com.ssq.product.model.OutboundRequest;
import com.ssq.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "产品库服务")
@RequestMapping("/product")
@RestController
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation(value = "产品入库", httpMethod = "POST")
    @RequestMapping("/inbound")
    public Result inbound(@RequestBody InboundRequest request){
        return productService.Inbound(request);
    }

    @ApiOperation(value = "产品出库", httpMethod = "POST")
    @RequestMapping("/outbound")
    public Result outbound(@RequestBody OutboundRequest request){
        return productService.Outbound(request);
    }

    @ApiOperation(value = "通过ID查询产品信息", httpMethod = "GET")
    @RequestMapping(value="/selectByProductId/{productId}", produces="application/json")
    public Result selectByProductId(@PathVariable Integer productId){
        return productService.SelectByProductId(productId);
    }

    @ApiOperation(value = "超时", httpMethod = "GET")
    @RequestMapping("/timeout")
    public Result timeout(){
        try{
            //睡5秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.ok("timeout");
    }
    @ApiOperation(value = "测试", httpMethod = "GET")
    @RequestMapping("/test")
    public Result test(){
        return Result.ok("测试");
    }


}
