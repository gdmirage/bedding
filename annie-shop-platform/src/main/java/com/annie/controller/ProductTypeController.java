package com.annie.controller;

import com.annie.constant.Constant;
import com.annie.constant.ResultCodeConstant;
import com.annie.dto.ResultDto;
import com.annie.entity.ProductType;
import com.annie.service.ProductTypeService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 16:58 2017/7/10 0010
 */
@RestController
@RequestMapping("/productType")
public class ProductTypeController extends BaseController {
    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    private final String VIEW_PATH = "/html/productType/";

    @RequestMapping(value = "/findProductTypePage")
    public ModelAndView findProductTypePage(@RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE) Integer pageSize,
                                            @RequestParam(value = "pageNum", defaultValue = Constant.PAGE_NUM) Integer pageNum,
                                            HttpServletRequest request) {
        PageInfo<ProductType> productTypePageInfo = productTypeService.findProductTypePage(null, pageSize, pageNum);

        // 返回给页面的所有参数
        returnMap = new HashMap<String, Object>();
        returnMap = setPaginatorParams(request);

        returnMap.put("productTypePageInfo", productTypePageInfo);

        ModelAndView mv = new ModelAndView();
        mv.addAllObjects(returnMap);
        mv.setViewName(VIEW_PATH + "product_type_list");
        return mv;
    }

    @RequestMapping(value = "/toCreateOrUpdateProductType")
    public ModelAndView toCreateOrUpdateProductType(Long productTypeId) {
        ModelAndView mv = new ModelAndView();
        ProductType productType = new ProductType();
        if (null != productTypeId) {
            productType = productTypeService.findProductTypeById(productTypeId);
        }
        List<ProductType> productTypeList = productTypeService.findAllProductTypeList();
        // 返回给页面的所有参数
        returnMap = new HashMap<String, Object>();
        returnMap.put("productTypeList", productTypeList);
        returnMap.put("productType", productType);
        mv.addAllObjects(returnMap);
        mv.setViewName(VIEW_PATH + "product_type_create_or_update");
        return mv;
    }

    @RequestMapping(value = "/createProductType")
    public ResultDto createProductType(String typeName,
                                       Integer parentId,
                                       String isUse) {
        ResultDto result = new ResultDto();
        try {
            ProductType productType = new ProductType();
            productType.setTypeName(typeName);
            productType.setParentId(parentId);
            productType.setIsUse(isUse);
            productType.setCreateMan(1);
            productTypeService.createProductType(productType);

            result.setResultCode(ResultCodeConstant.SUCCESS_CODE);
            result.setResultMsg(ResultCodeConstant.SUCCESS_MSG);
        } catch (Exception e) {
            result.setResultCode(ResultCodeConstant.EXCEPTION_CODE);
            result.setResultMsg(ResultCodeConstant.EXCEPTION_MSG);
            logger.error("ProductTypeController----createProductType  fail", e);
        }
        return result;
    }

    @RequestMapping(value = "/updateProductType")
    public ResultDto updateProductType(Long productTypeId,
                                       String typeName,
                                       Integer parentId,
                                       String isUse) {
        ResultDto result = new ResultDto();
        try {
            ProductType productType = productTypeService.findProductTypeById(productTypeId);
            productType.setTypeName(typeName);
            productType.setParentId(parentId);
            productType.setIsUse(isUse);
            productType.setCreateMan(1);
            productTypeService.updateProductType(productType);

            result.setResultCode(ResultCodeConstant.SUCCESS_CODE);
            result.setResultMsg(ResultCodeConstant.SUCCESS_MSG);
        } catch (Exception e) {
            result.setResultCode(ResultCodeConstant.EXCEPTION_CODE);
            result.setResultMsg(ResultCodeConstant.EXCEPTION_MSG);
            logger.error("ProductTypeController----createProductType  fail", e);
        }
        return result;
    }

    @RequestMapping(value = "/deleteProductType")
    public ResultDto deleteProductType(@RequestParam Integer productTypeId) {
        ResultDto result = new ResultDto();
        try {
            productTypeService.deleteProductType(productTypeId);
            result.setResultCode(ResultCodeConstant.SUCCESS_CODE);
            result.setResultMsg(ResultCodeConstant.SUCCESS_MSG);
        } catch (Exception e) {
            result.setResultCode(ResultCodeConstant.EXCEPTION_CODE);
            result.setResultMsg(ResultCodeConstant.EXCEPTION_MSG);
            logger.error("ProductTypeController----deleteProductType fail", e);
        }
        return result;
    }
}
