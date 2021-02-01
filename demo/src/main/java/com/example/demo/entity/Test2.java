package com.example.demo.entity;

public class Test2 {
    private Integer id;

    private String name;

    public Test2(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Test2() {
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