package com.example.demo.service.impl;

import com.example.demo.config.DataSource;
import com.example.demo.config.DataSourceNames;
import com.example.demo.dao.Test2Mapper;
import com.example.demo.dao.TestMapper;
import com.example.demo.entity.Test;
import com.example.demo.entity.Test2;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    TestMapper testMapper;

    @Autowired
    Test2Mapper test2Mapper;

    @Override
    public Test getName(Integer id) {
        return testMapper.selectByPrimaryKey(id);
    }


    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public Test2 getName2(Integer id) {
        return test2Mapper.selectByPrimaryKey(id);
    }
}
