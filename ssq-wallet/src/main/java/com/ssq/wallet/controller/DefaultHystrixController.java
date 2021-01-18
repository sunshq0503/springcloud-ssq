package com.ssq.wallet.controller;

import com.ssq.commons.response.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 默认降级处理
 */
@RestController
public class DefaultHystrixController {

    @RequestMapping("/defaultfallback")
    public Result defaultfallback(){
//        System.out.println("降级操作...");
//        Map<String,String> map = new HashMap<>();
//        map.put("resultCode","fail");
//        map.put("resultMessage","服务异常");
//        map.put("resultObj","null");
        return Result.error("熔断，降级操作");
    }
}