package com.example.demo.service;

import com.example.demo.entity.Test;
import com.example.demo.entity.Test2;
import org.springframework.web.bind.annotation.PathVariable;

public interface DemoService {
    Test getName(Integer id);
    Test2 getName2(Integer id);
}
