package com.annie.dao;


import com.annie.entity.ProductType;

public interface ProductTypeMapper {
    int insertSelective(ProductType record);

    ProductType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductType record);

}