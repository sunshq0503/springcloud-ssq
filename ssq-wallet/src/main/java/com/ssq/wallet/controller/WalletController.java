package com.ssq.wallet.controller;

import com.ssq.commons.response.Result;
import com.ssq.wallet.service.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "钱包服务")
@RequestMapping("/wallet")
@RestController
@Slf4j
public class WalletController {
    @Autowired
    WalletService walletService;

    @ApiOperation(value = "存入", httpMethod = "GET")
    @RequestMapping("/income/{amount}")
    Result income(@PathVariable Long amount){
        return walletService.income(amount);
    }

    @ApiOperation(value = "支出", httpMethod = "GET")
    @RequestMapping("/expenditure/{userId}/{amount}")
    Result expenditure(@PathVariable String userId,@PathVariable Long amount){
        return walletService.expenditure(userId,amount);
    }

    @ApiOperation(value = "创建钱包", httpMethod = "GET")
    @RequestMapping("/create")
    Result create(){
        return walletService.create();
    }

    @ApiOperation(value = "钱包查询", httpMethod = "GET")
    @RequestMapping("/select")
    Result select(){
        return walletService.select();
    }

    @ApiOperation(value = "查询余额", httpMethod = "GET")
    @RequestMapping("/getBalance/{userId}")
    Result getBalance(@PathVariable String userId){
        return walletService.getBalance(userId);
    }


}
