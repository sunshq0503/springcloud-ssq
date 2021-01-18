package com.ssq.order.entity;

import javax.persistence.*;

@Table(name = "order_detail")
public class OrderDetail {
    @Id
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    /**
     * 单价
     */
    private Long price;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 总金额
     */
    private Long total;

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
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取总金额
     *
     * @return total - 总金额
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置总金额
     *
     * @param total 总金额
     */
    public void setTotal(Long total) {
        this.total = total;
    }
}