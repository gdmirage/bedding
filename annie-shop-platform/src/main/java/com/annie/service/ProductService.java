package com.annie.service;

import com.annie.entity.Product;
import com.github.pagehelper.PageInfo;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 17:29 2017/7/10 0010
 */
public interface ProductService {

    PageInfo<Product> findProductPage(Product product, int pageSize, int pageNum);

    int createProduct(Product product);

    int updateProduct(Product product);

    Product findProductById(long productId);

    int deleteProduct(long productId);
}
