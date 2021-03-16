package com.ssq.ucenter.dao;

import com.ssq.ucenter.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User> {
    User selectById(String id);
}