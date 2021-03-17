package com.ssq.order.service.Impl;

import com.ssq.commons.constant.ParamConstant;
import com.ssq.commons.entity.Product;
import com.ssq.commons.enums.ResultCode;
import com.ssq.commons.feign.ProductFeign;
import com.ssq.commons.feign.WalletFeign;
import com.ssq.commons.response.Result;
import com.ssq.commons.utils.JsonUtil;
import com.ssq.order.dao.OrderDetailMapper;
import com.ssq.order.dao.OrderMapper;
import com.ssq.order.entity.Order;
import com.ssq.order.entity.OrderDetail;
import com.ssq.order.model.CreateRequest;
import com.ssq.order.model.mongo.OrderLog;
import com.ssq.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductFeign productFeign;

    @Autowired
    WalletFeign walletFeign;

    @Autowired
    MongoTemplate mongoTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result create(CreateRequest request) {
        Integer productId = request.getProductId();
        Result result = productFeign.SelectByProductId(productId);
        Product product = (Product) result.getData();
        if(product == null){
            return Result.error("产品信息不存在");
        }
        Integer number = request.getNumber();
        if(number > product.getNumber()){
            return Result.error("库存不足");
        }
        Long price =  product.getPrice();
        Long payment = number * price;
        HttpServletRequest _request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = _request.getHeader(ParamConstant.HEAD_USER_ID);

        result = walletFeign.getBalance(userId);
        Long balance = (Long)result.getData();
        if(balance < payment){
            return Result.error("钱包余额不足");
        }

        //创建订单
        Order order = new Order();
        order.setUserId(userId);
        order.setPayment(payment);
        order.setCreateTime(new Date());
        System.out.println(JsonUtil.object2Json(order));
        orderMapper.insert(order);

        //创建订单详情
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId());
        orderDetail.setProductId(productId);
        orderDetail.setNum(number);
        orderDetail.setPrice(price);
        orderDetail.setTotal(payment);
        orderDetailMapper.insert(orderDetail);

        //扣款
        result = walletFeign.expenditure(userId,payment);
        if(result.getStatus() != ResultCode.SUCCESS.getCode()){
            throw new RuntimeException("用户钱包异常");
        }

        OrderLog orderLog = new OrderLog();
        orderLog.setOrder(order);

        List<Product> list = new ArrayList<Product>();
        Product p = new Product();
        p.setId(productId);
        p.setPrice(price);
        p.setNumber(number);
        list.add(p);
        orderLog.setProducts(list);
        mongoTemplate.save(orderLog);

        return Result.ok();
    }

    @Override
    public Result test() {
        return productFeign.test();
    }
}
