package com.example.demo.dao;

import com.example.demo.entity.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
    Test selectByPrimaryKey(Integer id);
}