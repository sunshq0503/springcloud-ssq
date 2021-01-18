package com.ssq.wallet.service.Impl;

import com.ssq.commons.constant.ParamConstant;
import com.ssq.commons.response.Result;
import com.ssq.wallet.dao.WalletMapper;
import com.ssq.wallet.entity.Wallet;
import com.ssq.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletMapper walletMapper;

    @Override
    public Result income(Long amount) {
        HttpServletRequest _request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = _request.getHeader(ParamConstant.HEAD_USER_ID);
        Wallet wallet = walletMapper.selectByUserId(userId);
        if(wallet == null){
            return Result.error("用户数据异常");
        }
        int count = walletMapper.income(amount,userId);
        return Result.okCount(count);
    }

    @Override
    public Result expenditure(String userId,Long amount) {
        Wallet wallet = walletMapper.selectByUserId(userId);
        if(wallet == null){
            return Result.error("用户钱包不存在");
        }
        if(wallet.getBalance() < amount){
            return Result.error("用户钱包余额不足");
        }
        int count = walletMapper.expenditure(amount,userId);
        return Result.okCount(count);
    }

    @Override
    public Result create() {
        HttpServletRequest _request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = _request.getHeader(ParamConstant.HEAD_USER_ID);
        Wallet wallet = walletMapper.selectByUserId(userId);
        if(wallet != null){
            return Result.error("用户钱包已存在");
        }
        wallet = new Wallet();
        wallet.setBalance((long) 0);
        wallet.setUserId(userId);
        int count = walletMapper.insert(wallet);
        return Result.okCount(count);
    }

    @Override
    public Result select() {
        HttpServletRequest _request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = _request.getHeader(ParamConstant.HEAD_USER_ID);
        Wallet wallet = walletMapper.selectByUserId(userId);
        if(wallet == null){
            return Result.error("用户钱包不存在");
        }
        return Result.ok(wallet);
    }

    @Override
    public Long getBalance(String userId) {
        Wallet wallet = walletMapper.selectByUserId(userId);
        if(wallet == null){
            return 0L;
        }
        return wallet.getBalance();
    }
}
