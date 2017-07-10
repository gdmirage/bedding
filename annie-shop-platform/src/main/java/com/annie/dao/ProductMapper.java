package com.annie.dao;


import com.annie.entity.Product;

public interface ProductMapper {

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long productId);

    int updateByPrimaryKeySelective(Product record);

}
