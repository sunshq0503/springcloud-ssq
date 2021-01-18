package com.ssq.order.dao;

import com.ssq.order.entity.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface OrderMapper extends Mapper<Order> {
}