package com.annie.service;

import com.annie.entity.ProductType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 17:29 2017/7/10 0010
 */
public interface ProductTypeService {

    PageInfo<ProductType> findProductTypePage(ProductType productType, int pageSize, int pageNum);

    int createProductType(ProductType productType);

    int updateProductType(ProductType productType);

    ProductType findProductTypeById(int productTypeId);

    int deleteProductType(int productTypeId);
}
