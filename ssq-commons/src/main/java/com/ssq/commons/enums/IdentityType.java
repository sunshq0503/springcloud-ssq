package com.ssq.commons.enums;

public enum IdentityType {
    PHONE_VERIFY(0,"手机号+验证码"),
    PHONE_PASSWORD(1,"手机号+密码"),
    EMAIL_PASSWORD(2,"邮箱+密码"),
    USERNAME_PASSWORD(3,"用户名+密码"),
    THIRD_WEIXIN(4,"三方-微信"),
    THIRD_WEIBO(5,"三方-微博");

    private Integer code;

    private String type;

    IdentityType(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getType() {
        return this.type;
    }
}
