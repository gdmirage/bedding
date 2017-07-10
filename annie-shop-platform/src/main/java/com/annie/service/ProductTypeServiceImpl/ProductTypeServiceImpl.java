package com.annie.service.ProductTypeServiceImpl;

import com.annie.dao.ProductTypeMapper;
import com.annie.entity.ProductType;
import com.annie.service.ProductTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 17:29 2017/7/10 0010
 */
@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService{

    private ProductTypeMapper productTypeMapper;

    @Override
    public PageInfo<ProductType> findProductTypePage(ProductType productType, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return null;
    }

    @Override
    public int createProductType(ProductType productType) {
        productType.setCreateDate(new Date());
        productType.setIsDelete("N");
        return productTypeMapper.insertProductType(productType);
    }

    @Override
    public int updateProductType(ProductType productType) {
        productType.setModifyDate(new Date());
        return productTypeMapper.updateProductType(productType);
    }

    @Override
    public ProductType findProductTypeById(int productTypeId) {
        return productTypeMapper.selectByPrimaryKey(productTypeId);
    }

    @Override
    public int deleteProductType(int productTypeId) {
        ProductType productType = productTypeMapper.selectByPrimaryKey(productTypeId);
        productType.setIsDelete("Y");
        return productTypeMapper.updateProductType(productType);
    }
}
