package com.annie.dao;


import com.annie.entity.ProductType;

public interface ProductTypeMapper {
    int insertProductType(ProductType record);

    ProductType selectByPrimaryKey(Integer id);

    int updateProductType(ProductType record);

}