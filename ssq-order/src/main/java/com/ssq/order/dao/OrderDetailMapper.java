package com.ssq.order.dao;

import com.ssq.order.entity.OrderDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface OrderDetailMapper extends Mapper<OrderDetail> {
}