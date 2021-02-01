package com.example.demo.controller;

import com.example.demo.dao.TestMapper;
import com.example.demo.entity.Test;
import com.example.demo.entity.Test2;
import com.example.demo.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {
    @Autowired
    DemoService demoService;

    @GetMapping("/getName/{id}")
    public Test getName(@PathVariable Integer id){
        return demoService.getName(id);
    }

    @GetMapping("/getName2/{id}")
    public Test2 getName2(@PathVariable Integer id){
        return demoService.getName2(id);
    }
}
