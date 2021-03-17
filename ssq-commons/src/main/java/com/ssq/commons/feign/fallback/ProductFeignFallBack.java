package com.ssq.commons.feign.fallback;

import com.ssq.commons.feign.ProductFeign;
import com.ssq.commons.response.Result;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignFallBack implements ProductFeign {
    @Override
    public Result test() {
        return Result.error("熔断！method:{ProductFeign.test}");
    }

    @Override
    public Result SelectByProductId(Integer productId) {
        return Result.error("熔断！method:{ProductFeign.SelectByProductId},productId="+productId);
    }
}
