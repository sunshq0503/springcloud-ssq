package com.ssq.commons.entity;

import javax.persistence.*;

@Table(name = "product")
public class Product {
    @Id
    private Integer id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品库存数量
     */
    private Integer number;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品名
     *
     * @return name - 商品名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名
     *
     * @param name 商品名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取商品库存数量
     *
     * @return number - 商品库存数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置商品库存数量
     *
     * @param number 商品库存数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
}