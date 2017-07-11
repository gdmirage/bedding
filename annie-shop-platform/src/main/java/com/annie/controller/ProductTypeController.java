package com.annie.controller;

import com.annie.constant.Constant;
import com.annie.dto.ResultDto;
import com.annie.entity.ProductType;
import com.annie.service.ProductTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    private final String VIEW_PATH = "/html/productType/";

    @RequestMapping(value = "/findProductTypePage")
    public ResultDto<PageInfo<ProductType>> findProductTypePage(@RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE) int pageSize,
                                                                 @RequestParam(value = "pageNum", defaultValue = Constant.PAGE_NUM) int pageNum){
        ResultDto<PageInfo<ProductType>> resultDto=new ResultDto<PageInfo<ProductType>>();
        PageInfo<ProductType> productTypePageInfo = productTypeService.findProductTypePage(null, pageSize, pageNum);
        resultDto.setResultData(productTypePageInfo);
        resultDto.setResultCode("");
        resultDto.setResultMsg("");
        ModelAndView mv = new ModelAndView();
        mv.addObject(productTypePageInfo);
        mv.setViewName(VIEW_PATH + "product_type_list");
        return resultDto;
    }

    @RequestMapping(value = "/create")
    public void createProductType(@RequestParam("typeName") String typeName, @RequestParam("parentId") Integer parentId, @RequestParam("isUse") String isUse, @RequestParam("picPath") String picPath) {
        ProductType productType = new ProductType();
        productType.setTypeName(typeName);
        productType.setParentId(parentId);
        productType.setIsUse(isUse);
        productType.setPicPath(picPath);
        productType.setCreateMan(1);
        productTypeService.createProductType(productType);
    }
}
