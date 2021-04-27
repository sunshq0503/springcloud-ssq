package com.ssq.order.controller;

import com.ssq.commons.response.Result;
import com.ssq.order.model.CreateRequest;
import com.ssq.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单服务")
@RequestMapping("/order")
@RestController
@Slf4j
public class OrderController {
    @Value("${server.port}")
    private String port ;

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "创建订单", httpMethod = "POST")
    @RequestMapping("/create")
    public Result create(@RequestBody CreateRequest request) {
        return orderService.create(request);
    }

    @ApiOperation(value = "feign", httpMethod = "GET")
    @RequestMapping("/feign")
    public Result feign() {
        return orderService.test();
    }

    @ApiOperation(value = "测试", httpMethod = "GET")
    @RequestMapping("/test")
    public Result test(){
        return Result.ok("测试"+port);
    }


}
