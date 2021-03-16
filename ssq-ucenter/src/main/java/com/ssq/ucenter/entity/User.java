package com.ssq.ucenter.entity;

import java.util.Date;

public class User {
    private String id;

    private String nickname;

    private String avatar;

    private Date registTime;

    public User(String id, String nickname, String avatar, Date registTime) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.registTime = registTime;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
}