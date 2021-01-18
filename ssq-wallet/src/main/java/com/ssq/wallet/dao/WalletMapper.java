package com.ssq.wallet.dao;

import com.ssq.wallet.entity.Wallet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface WalletMapper extends Mapper<Wallet> {
    int income(@Param("amount")Long amount, @Param("userId")String userId);
    int expenditure(@Param("amount")Long amount, @Param("userId")String userId);
    Wallet selectByUserId(String userId);
}