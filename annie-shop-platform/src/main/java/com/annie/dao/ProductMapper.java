package com.annie.dao;


import com.annie.entity.Product;
import com.annie.entity.ProductType;

import java.util.List;

public interface ProductMapper {

    int insertProduct(Product record);

    Product selectByPrimaryKey(Long productId);

    int updateProduct(Product record);

    List<Product> selectProductList();
}
