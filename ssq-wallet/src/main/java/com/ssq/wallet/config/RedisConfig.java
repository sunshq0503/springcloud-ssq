package com.ssq.wallet.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *  redis 配置
 **/
@Slf4j
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        log.info("------------------- redis  装配  开始 -------------------------");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();

        redisTemplate.setConnectionFactory(factory);

        // Json序列化配置
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(objectMapper);

        // String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key均采用String序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hsah的key也采用String序列化的方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // value采用json的序列化方式
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        // hash的value也采用json的序列化方式
        redisTemplate.setHashKeySerializer(jsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        log.info("------------------- redis  装配  结束 -------------------------");
        return redisTemplate;


    }


}
