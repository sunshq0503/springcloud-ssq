package com.example.demo.dao;

import com.example.demo.entity.Test2;
import org.springframework.stereotype.Repository;

@Repository
public interface Test2Mapper {
    Test2 selectByPrimaryKey(Integer id);
}