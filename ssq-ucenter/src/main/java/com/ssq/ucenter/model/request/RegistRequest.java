package com.ssq.ucenter.model.request;

import lombok.Data;

@Data
public class RegistRequest {
    /**
     * 登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）
     */
    public Integer identityType;

    /**
     * 登录标识（手机号 邮箱 用户名或第三方应用的唯一标识）
     */
    public String identifier;

    /**
     * 密码凭证（站内的保存密码，站外的不保存）
     */
    public String credential;
}
