package com.ssq.order.service;

import com.ssq.commons.response.Result;
import com.ssq.order.model.CreateRequest;

public interface OrderService {
    Result create(CreateRequest request);
    Result test();
}
