package com.ssq.ucenter.controller;

import com.ssq.commons.enums.IdentityType;
import com.ssq.commons.enums.ResultCode;
import com.ssq.commons.response.Result;
import com.ssq.ucenter.model.request.*;
import com.ssq.ucenter.service.MailService;
import com.ssq.ucenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(tags = "用户服务")
@RequestMapping("/ucenter")
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @RequestMapping("/regist")
    public Result regist(@RequestBody RegistRequest request){
        return userService.regist(request);
    }

    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @RequestMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @ApiOperation(value = "傻瓜式用户注册", httpMethod = "GET")
    @RequestMapping("/create")
    public Result create(){
        return userService.create();
    }

    @ApiOperation(value = "修改密码", httpMethod = "POST")
    @RequestMapping("/modifyPwd")
    public Result modifyPwd(@RequestBody ModifyPwdRequest modifyPwdRequest){
        return userService.modifyPwd(modifyPwdRequest);
    }

    @ApiOperation(value = "忘记密码", httpMethod = "GET")
    @RequestMapping("/forgetPwd")
    public Result forgetPwd(@RequestBody ForgetPwdRequest request){
        Result result = userService.forgetPwd(request);
        if(result.getStatus() == ResultCode.SUCCESS.getCode()){
            if(request.getIdentityType() == IdentityType.EMAIL_PASSWORD.getCode()){
                mailService.sendSimpleMail(request.getIdentifier(),"邮箱登录密码重置验证码",((HashMap)result.getData()).get("verifyCode").toString());
            }
        }
        return result;
    }

    @ApiOperation(value = "重置密码", httpMethod = "POST")
    @RequestMapping("/resetPwd")
    public Result resetPwd(@RequestBody ResetPwdRequest resetPwdRequest){
        return userService.resetPwd(resetPwdRequest);
    }

    @ApiOperation(value = "账户绑定", httpMethod = "POST")
    @RequestMapping("/bind")
    public Result bind(@RequestBody BindRequest bindRequest){
        return userService.bind(bindRequest);
    }

    @ApiOperation(value = "测试", httpMethod = "GET")
    @RequestMapping("/hello")
    public Result hello(){
        return Result.ok("hello user123");
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


}
