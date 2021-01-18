package com.ssq.product.service.Impl;

import com.ssq.commons.entity.Product;
import com.ssq.commons.response.Result;
import com.ssq.product.dao.ProductMapper;
import com.ssq.product.model.InboundRequest;
import com.ssq.product.model.OutboundRequest;
import com.ssq.product.service.ProductService;
import com.ssq.product.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Result Inbound(InboundRequest request) {
        Integer number = request.getNumber();
        Long price = request.getPrice();
        String name = request.getName();
        if(StringUtils.isBlank(name) ){
            return Result.error("参数错误，name不能为空");
        }
        if(number == 0){
            return Result.error("参数错误，number不能为0");
        }
        if(price == 0){
            return Result.error("参数错误，price不能为0");
        }
        Product product = productMapper.selectByName(name);
        if(product == null){
            product = new Product();
            product.setName(name);
            product.setNumber(number);
            product.setPrice(price);
            int id = productMapper.insert(product);
            product.setId(id);
            return Result.ok(product);
        }else{
            int count = productMapper.updateByPrimaryKey(product);
            return Result.okCount(count);
        }
    }

    @Override
    public Result Outbound(OutboundRequest request) {
        Integer productId = request.getProductId();
        Integer number = request.getNumber();
        if(number == 0){
            return Result.error("参数错误，number不能为0");
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if(product == null){
            return Result.error("非法请求，数据错误1");
        }
        if(product.getNumber() < number){
            return Result.error("非法请求，数据错误2");
        }
        product.setNumber(product.getNumber()-number);
        int count = productMapper.updateByPrimaryKey(product);
        return Result.okCount(count);
    }

    @Override
    public Product SelectByProductId(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        return product;
    }
}
