package com.annie.controller;

import com.annie.entity.ProductType;
import com.annie.service.ProductTypeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 16:58 2017/7/10 0010
 */
@RestController
@RequestMapping("/productType")
public class ProductTypeController {
    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    public void createProductType(@PathVariable("typeName") String typeName, @PathVariable("parentId") Integer parentId, @PathVariable("isUse") String isUse, @PathVariable("picPath") String picPath) {
        ProductType productType = new ProductType();
        productType.setTypeName(typeName);
        productType.setParentId(parentId);
        productType.setIsUse(isUse);
        productType.setPicPath(picPath);
        productType.setCreateMan(1);
        productTypeService.createProductType(productType);
    }
}
