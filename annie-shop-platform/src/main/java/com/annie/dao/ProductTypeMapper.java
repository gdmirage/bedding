package com.annie.dao;


import com.annie.entity.ProductType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductTypeMapper {
    int insertProductType(ProductType record);

    ProductType selectByPrimaryKey(Long id);

    int updateProductType(ProductType record);

    List<ProductType> selectProductTypeList();
}