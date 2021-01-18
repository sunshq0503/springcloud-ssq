package com.ssq.product.dao;

import com.ssq.commons.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface ProductMapper extends Mapper<Product> {
    int insertBy3Params(@Param("name")String name,@Param("price")Long price,@Param("number")Integer number);
    Product selectByName(String name);
    int updateById(@Param("product")Product product);
}