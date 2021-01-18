package com.ssq.commons.constant;

public class RedisConstant {
    public static final String KEY_FORGET_PASSWORD = "forgetPwd:%s:verifyCode";
    public static final int EXPIRE_FORGET_PASSWORD = 60;//秒
    public static final String KEY_USER = "user:%s:token";
    public static final int EXPIRE_USER = 600;//秒
}
