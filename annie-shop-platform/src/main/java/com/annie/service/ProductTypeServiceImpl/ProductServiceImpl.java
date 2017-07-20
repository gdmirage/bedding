package com.annie.service.ProductTypeServiceImpl;

import com.annie.dao.ProductMapper;
import com.annie.dao.ProductTypeMapper;
import com.annie.entity.Product;
import com.annie.entity.ProductType;
import com.annie.service.ProductService;
import com.annie.service.ProductTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 17:29 2017/7/10 0010
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> findProductPage(Product product, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectProductList();
        PageInfo<Product> productPageInfo = new PageInfo<Product>(productList);
        return productPageInfo;
    }

    @Override
    public int createProduct(Product product) {
        product.setCreateDate(new Date());
        product.setIsDelete("N");
        return productMapper.insertProduct(product);
    }

    @Override
    public int updateProduct(Product product) {
        product.setModifyDate(new Date());
        return productMapper.updateProduct(product);
    }

    @Override
    public Product findProductById(long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    @Override
    public int deleteProduct(long productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        product.setIsDelete("Y");
        product.setModifyDate(new Date());
        return productMapper.updateProduct(product);
    }

}
