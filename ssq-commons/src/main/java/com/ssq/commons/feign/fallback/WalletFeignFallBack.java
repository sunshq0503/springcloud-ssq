package com.ssq.commons.feign.fallback;

import com.ssq.commons.feign.WalletFeign;
import com.ssq.commons.response.Result;
import org.springframework.stereotype.Component;

@Component
public class WalletFeignFallBack implements WalletFeign {
    @Override
    public Result expenditure(String userId, Long amount) {
        return Result.error("熔断！method=WalletFeign.expenditure，{userId="+userId+"，amount="+amount+"}");
    }

    @Override
    public Result getBalance(String userId) {
        return Result.error("熔断！method=WalletFeign.getBalance，{userId="+userId+"}");
    }
}
