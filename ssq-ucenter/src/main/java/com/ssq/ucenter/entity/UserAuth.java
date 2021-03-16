package com.ssq.ucenter.entity;

import java.util.Date;

public class UserAuth {
    private Integer id;

    private String userId;

    private Byte identityType;

    private String identifier;

    private String credential;

    private Date bindTime;

    private Date loginTime;

    private String loginIp;

    private Integer loginCount;

    public UserAuth(Integer id, String userId, Byte identityType, String identifier, String credential, Date bindTime, Date loginTime, String loginIp, Integer loginCount) {
        this.id = id;
        this.userId = userId;
        this.identityType = identityType;
        this.identifier = identifier;
        this.credential = credential;
        this.bindTime = bindTime;
        this.loginTime = loginTime;
        this.loginIp = loginIp;
        this.loginCount = loginCount;
    }

    public UserAuth() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Byte getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Byte identityType) {
        this.identityType = identityType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential == null ? null : credential.trim();
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
}