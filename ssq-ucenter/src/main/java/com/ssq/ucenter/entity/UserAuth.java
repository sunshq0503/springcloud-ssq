package com.ssq.ucenter.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_auth")
public class UserAuth {
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）
     */
    @Column(name = "identity_type")
    private Byte identityType;

    /**
     * 登录标识（手机号 邮箱 用户名或第三方应用的唯一标识）
     */
    private String identifier;

    /**
     * 密码凭证（站内的保存密码，站外的不保存）
     */
    private String credential;

    /**
     * 绑定时间
     */
    @Column(name = "bind_time")
    private Date bindTime;

    /**
     * 最近一次登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 最近一次登录ip
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    private Integer loginCount;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）
     *
     * @return identity_type - 登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）
     */
    public Byte getIdentityType() {
        return identityType;
    }

    /**
     * 设置登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）
     *
     * @param identityType 登录类型（手机号 邮箱 用户名）或第三方应用名称（微信 微博等）
     */
    public void setIdentityType(Byte identityType) {
        this.identityType = identityType;
    }

    /**
     * 获取登录标识（手机号 邮箱 用户名或第三方应用的唯一标识）
     *
     * @return identifier - 登录标识（手机号 邮箱 用户名或第三方应用的唯一标识）
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * 设置登录标识（手机号 邮箱 用户名或第三方应用的唯一标识）
     *
     * @param identifier 登录标识（手机号 邮箱 用户名或第三方应用的唯一标识）
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * 获取密码凭证（站内的保存密码，站外的不保存）
     *
     * @return credential - 密码凭证（站内的保存密码，站外的不保存）
     */
    public String getCredential() {
        return credential;
    }

    /**
     * 设置密码凭证（站内的保存密码，站外的不保存）
     *
     * @param credential 密码凭证（站内的保存密码，站外的不保存）
     */
    public void setCredential(String credential) {
        this.credential = credential;
    }

    /**
     * 获取绑定时间
     *
     * @return bind_time - 绑定时间
     */
    public Date getBindTime() {
        return bindTime;
    }

    /**
     * 设置绑定时间
     *
     * @param bindTime 绑定时间
     */
    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    /**
     * 获取最近一次登录时间
     *
     * @return login_time - 最近一次登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置最近一次登录时间
     *
     * @param loginTime 最近一次登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取最近一次登录ip
     *
     * @return login_ip - 最近一次登录ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置最近一次登录ip
     *
     * @param loginIp 最近一次登录ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取登录次数
     *
     * @return login_count - 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登录次数
     *
     * @param loginCount 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}