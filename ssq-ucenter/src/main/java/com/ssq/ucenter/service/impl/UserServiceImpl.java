package com.ssq.ucenter.service.impl;

import com.ssq.commons.constant.ParamConstant;
import com.ssq.commons.constant.RedisConstant;
import com.ssq.commons.enums.IdentityType;
import com.ssq.commons.model.response.LoginResponse;
import com.ssq.commons.response.Result;
import com.ssq.commons.utils.Utils;
import com.ssq.ucenter.dao.UserAuthMapper;
import com.ssq.ucenter.dao.UserMapper;
import com.ssq.ucenter.entity.User;
import com.ssq.ucenter.entity.UserAuth;
import com.ssq.ucenter.model.request.*;
import com.ssq.ucenter.model.response.RegistResponse;
import com.ssq.ucenter.service.UserService;
import com.ssq.ucenter.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserAuthMapper userAuthMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Result login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        Integer identityType = request.getIdentityType();
        String identifier = request.getIdentifier();
        String credential = request.getCredential();
        UserAuth userAuth = null;
        if(identityType == IdentityType.PHONE_VERIFY.getCode()){
            if (StringUtils.isBlank(identifier) || !identifier.matches("^1\\d{10}$")) {
                return Result.error("手机号错误");
            }
        }else if(identityType == IdentityType.PHONE_PASSWORD.getCode()) {
            if (StringUtils.isBlank(identifier) || !identifier.matches("^1\\d{10}$")) {
                return Result.error("手机号错误");
            }
            if (StringUtils.isBlank(credential) || credential.length() < 6) {
                return Result.error("密码格式错误");
            }
            userAuth = userAuthMapper.selectByIdentifierAndCredential(identifier, credential);
            if(userAuth == null){
                return Result.error("手机号或密码错误");
            }
        }else if(identityType == IdentityType.EMAIL_PASSWORD.getCode()){
            if (!Utils.isEmail(identifier)){
                return Result.error("邮箱格式错误");
            }
            if (StringUtils.isBlank(credential) || credential.length() < 6) {
                return Result.error("密码格式错误");
            }
            userAuth = userAuthMapper.selectByIdentifierAndCredential(identifier, credential);
            if(userAuth == null){
                return Result.error("邮箱或密码错误");
            }
        }else if(identityType == IdentityType.USERNAME_PASSWORD.getCode()){
            if (StringUtils.isBlank(identifier) || !identifier.matches("[0-9A-Za-z_]*")){
                return Result.error("用户名格式错误");
            }
            if (StringUtils.isBlank(credential) || credential.length() < 6) {
                return Result.error("密码格式错误");
            }
            userAuth = userAuthMapper.selectByIdentifierAndCredential(identifier, credential);
            if(userAuth == null){
                return Result.error("用户名或密码错误");
            }
        }else if(identityType == IdentityType.THIRD_WEIXIN.getCode()){


        }else if(identityType == IdentityType.THIRD_WEIBO.getCode()){


        }else{
            return Result.error("参数错误");
        }
        String token = Utils.createToken();
        String uid = userAuth.getUserId();
        String key = String.format(RedisConstant.KEY_USER,uid);
        if(redisUtil.hasKey(key)){
            return Result.error("重复登录");
        }
        userAuth.setLoginTime(new Date());
        userAuth.setLoginCount(userAuth.getLoginCount()+1);
        userAuthMapper.updateByPrimaryKey(userAuth);

        User user = userMapper.selectById(uid);
        response.setUaid(userAuth.getId());
        response.setUid(uid);
        response.setNickName(user.getNickname());
        response.setToken(token);
        response.setIdentifier(identifier);
        response.setIdentityType(identityType);

        redisUtil.set(key,response, RedisConstant.EXPIRE_USER);
        return Result.ok(response);
    }

    @Override
    public Result regist(RegistRequest request) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();
        String ip = Utils.getIp(httpServletRequest);

        RegistResponse response = new RegistResponse();
        Integer identityType = request.getIdentityType();
        String identifier = request.getIdentifier();
        String credential = request.getCredential();
        if(identityType == IdentityType.PHONE_VERIFY.getCode()){
            if (StringUtils.isBlank(identifier) || !identifier.matches("^1\\d{10}$")) {
                return Result.error("手机号错误");
            }
            User user = createUser();
            userMapper.insert(user);
            UserAuth userAuth = createUserAuth(user.getId(),identityType,identifier,null,ip);
            userAuthMapper.insert(userAuth);
        }else if(identityType == IdentityType.PHONE_PASSWORD.getCode()) {
            if (StringUtils.isBlank(identifier) || !identifier.matches("^1\\d{10}$")) {
                return Result.error("手机号错误");
            }
            if (StringUtils.isBlank(credential) || credential.length() < 6) {
                return Result.error("密码格式错误");
            }
            User user = createUser();
            userMapper.insert(user);
            UserAuth userAuth = createUserAuth(user.getId(),identityType,identifier,credential,ip);
            userAuthMapper.insert(userAuth);

        }else if(identityType == IdentityType.EMAIL_PASSWORD.getCode()){
            if (!Utils.isEmail(identifier)){
                return Result.error("邮箱格式错误");
            }
            if (StringUtils.isBlank(credential) || credential.length() < 6) {
                return Result.error("密码格式错误");
            }
            User user = createUser();
            userMapper.insert(user);
            UserAuth userAuth = createUserAuth(user.getId(),identityType,identifier,credential,ip);
            userAuthMapper.insert(userAuth);
        }else if(identityType == IdentityType.USERNAME_PASSWORD.getCode()){
            if (StringUtils.isBlank(identifier) || !identifier.matches("[0-9A-Za-z_]*")){
                return Result.error("用户名格式错误");
            }
            if (StringUtils.isBlank(credential) || credential.length() < 6) {
                return Result.error("密码格式错误");
            }
            User user = createUser();
            userMapper.insert(user);
            UserAuth userAuth = createUserAuth(user.getId(),identityType,identifier,credential,ip);
            userAuthMapper.insert(userAuth);
        }else if(identityType == IdentityType.THIRD_WEIXIN.getCode()){


        }else if(identityType == IdentityType.THIRD_WEIBO.getCode()){


        }else{
            return Result.error("参数错误");
        }
        return Result.ok(response);
    }

    @Override
    public Result bind(BindRequest request) {
        Integer identityType = request.getIdentityType();
        String identifier = request.getIdentifier();

        HttpServletRequest _request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = _request.getHeader(ParamConstant.HEAD_USER_ID);
        String key = String.format(RedisConstant.KEY_USER,userId);
        LoginResponse response = (LoginResponse) redisUtil.get(key);
        Integer uaid = response.getUaid();
        Integer m_identityType = response.getIdentityType();

        if(m_identityType.intValue() == identityType.intValue()){
            return Result.error("错误请求");
        }
        if(identityType == IdentityType.PHONE_PASSWORD.getCode()) {
            if (StringUtils.isBlank(identifier) || !identifier.matches("^1\\d{10}$")) {
                return Result.error("手机号错误");
            }
        }else if(identityType == IdentityType.EMAIL_PASSWORD.getCode()){
            if (!Utils.isEmail(identifier)){
                return Result.error("邮箱格式错误");
            }
        }else if(identityType == IdentityType.USERNAME_PASSWORD.getCode()){
            if (StringUtils.isBlank(identifier) || !identifier.matches("[0-9A-Za-z_]*")){
                return Result.error("用户名格式错误");
            }
        }else{
            return Result.error("参数错误");
        }
        UserAuth m_userAuth= userAuthMapper.selectByPrimaryKey(uaid);
        UserAuth userAuth = createUserAuthBind(userId,identityType,identifier,m_userAuth.getCredential());
        int count = userAuthMapper.insert(userAuth);
        return Result.okCount(count);
    }

    @Override
    public Result create() {
        User user = createUser();
        userMapper.insert(user);
        return Result.ok(user);
    }

    @Override
    public Result modifyPwd(ModifyPwdRequest request) {
        String credential = request.getCredential();
        String newCredential = request.getNewCredential();
        if (StringUtils.isBlank(credential) || credential.length() < 6) {
            return Result.error("密码格式错误");
        }

        HttpServletRequest _request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = _request.getHeader(ParamConstant.HEAD_USER_ID);
        String key = String.format(RedisConstant.KEY_USER,userId);
        LoginResponse response = (LoginResponse) redisUtil.get(key);

        UserAuth userAuth = userAuthMapper.selectByIdentifierAndCredential(response.getIdentifier(), credential);
        if(userAuth == null){
            return Result.error("请输入正确原密码");
        }
        int count = userAuthMapper.updateCredentialByUid(userId, newCredential);
        return Result.okCount(count);
    }

    @Override
    public Result forgetPwd(ForgetPwdRequest request) {
        String identifier = request.getIdentifier();
        Integer identityType = request.getIdentityType();

        if(identityType == IdentityType.PHONE_PASSWORD.getCode()) {
            if (StringUtils.isBlank(identifier) || !identifier.matches("^1\\d{10}$")) {
                return Result.error("手机号错误");
            }
            return Result.error("目前不支持手机号找回登录密码");
        }else if(identityType == IdentityType.EMAIL_PASSWORD.getCode()){
            if (!Utils.isEmail(identifier)){
                return Result.error("邮箱格式错误");
            }
            String key = String.format(RedisConstant.KEY_FORGET_PASSWORD,identifier);
            if(redisUtil.hasKey(key)){
                return Result.error("验证码已发送至注册邮箱");
            }
            String verifyCode = Utils.createVerifyCode(5);
            redisUtil.set(key,verifyCode,RedisConstant.EXPIRE_FORGET_PASSWORD);
            Map map = new HashMap();
            map.put("verifyCode",verifyCode);
            return Result.ok(map);
        }else if(identityType == IdentityType.USERNAME_PASSWORD.getCode()){
            if (StringUtils.isBlank(identifier) || !identifier.matches("[0-9A-Za-z_]*")){
                return Result.error("用户名格式错误");
            }
            return Result.error("目前不支持用户名找回登录密码");
        }
        return Result.error("参数错误");
    }

    @Override
    public Result resetPwd(ResetPwdRequest request) {
        String credential = request.getCredential();
        if (StringUtils.isBlank(credential) || credential.length() < 6) {
            return Result.error("密码格式错误");
        }
        String identifier = request.getIdentifier();
        String key = String.format(RedisConstant.KEY_FORGET_PASSWORD,identifier);
        if(!redisUtil.hasKey(key)){
            return Result.error("验证码已过期");
        }
        String m_verifyCode = redisUtil.get(key).toString();
        String verifyCode = request.getVerifyCode();
        if(!verifyCode.toLowerCase().equals(m_verifyCode.toLowerCase())){
            return Result.error("验证码错误");
        }
        int count = userAuthMapper.updateCredentialByIdentifier(identifier, credential);
        return Result.okCount(count);
    }

    public User createUser(){
        User user = new User();
        user.setId(Utils.createUUID());
        user.setAvatar(null);
        user.setNickname("游客");
        user.setRegistTime(new Date());
        return user;
    }

    public UserAuth createUserAuth(String userId,Integer identityType,String identifier,String credential,String ip){
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(userId);
        userAuth.setIdentityType((byte)identityType.intValue());
        userAuth.setIdentifier(identifier);
        userAuth.setCredential(credential);
        Date date = new Date();
        userAuth.setBindTime(date);
        userAuth.setLoginTime(date);
        userAuth.setLoginIp(ip);
        userAuth.setLoginCount(1);
        return userAuth;
    }

    public UserAuth createUserAuthBind(String userId,Integer identityType,String identifier,String credential){
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(userId);
        userAuth.setIdentityType((byte)identityType.intValue());
        userAuth.setIdentifier(identifier);
        userAuth.setCredential(credential);
        Date date = new Date();
        userAuth.setBindTime(date);
        userAuth.setLoginCount(0);
        return userAuth;
    }
}
