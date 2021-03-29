package com.ssq.product.service;

import com.ssq.commons.entity.Product;
import com.ssq.commons.response.Result;
import com.ssq.product.model.InboundRequest;
import com.ssq.product.model.OutboundRequest;

public interface ProductService {
    Result Inbound(InboundRequest request);
    Result Outbound(OutboundRequest request);
    Result SelectByProductId(Integer productId);
}
