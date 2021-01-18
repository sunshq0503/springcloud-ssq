package com.ssq.wallet.entity;

import javax.persistence.*;

@Table(name = "wallet")
public class Wallet {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 余额
     */
    private Long balance;

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
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取余额
     *
     * @return balance - 余额
     */
    public Long getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(Long balance) {
        this.balance = balance;
    }
}