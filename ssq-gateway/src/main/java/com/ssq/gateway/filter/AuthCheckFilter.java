package com.ssq.gateway.filter;

import com.ssq.commons.constant.AuthWhiteListConstant;
import com.ssq.commons.constant.ParamConstant;
import com.ssq.commons.constant.RedisConstant;
import com.ssq.commons.model.response.LoginResponse;
import com.ssq.gateway.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthCheckFilter extends AbstractGatewayFilterFactory {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            //获取用户ID
            String userId = request.getHeaders().getFirst(ParamConstant.HEAD_USER_ID);
            //获取token
            String token = request.getHeaders().getFirst(ParamConstant.HEAD_TOKEN);
            String path = request.getURI().getPath();
            //如果是白名单队列，直接放行
            for (String str : AuthWhiteListConstant.PATH){
                if(path.equals(str)){
                    return chain.filter(exchange.mutate().request(request).build());
                }
            }
            String key = String.format(RedisConstant.KEY_USER,userId);
            log.info("当前请求的url:{}, method:{},key:{}", request.getURI().getPath(), request.getMethodValue(),key);
            if(!redisUtil.hasKey(key)){
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
//                return Result.error("未登录用户，请先登录");
            }
            LoginResponse _response = (LoginResponse) redisUtil.get(key);
            String m_token = _response.getToken();
            if(!m_token.equals(token)){
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
//                return Result.error("非法用户，请重新登录");
            }
            redisUtil.set(key,_response, RedisConstant.EXPIRE_USER);
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}
