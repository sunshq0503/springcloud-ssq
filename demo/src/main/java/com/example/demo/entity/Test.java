package com.example.demo.entity;

public class Test {
    private Integer id;

    private String name;

    public Test(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Test() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}