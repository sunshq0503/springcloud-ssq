package com.ssq.wallet.service;

import com.ssq.commons.response.Result;

public interface WalletService {
    Result income(Long amount);
    Result expenditure(String userId,Long amount);
    Result create();
    Result select();
    Long getBalance(String userId);
}
