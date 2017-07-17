package com.annie.controller;

import com.annie.entity.ProductType;
import com.annie.service.ProductTypeService;
import com.annie.utils.FileUtil;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Blade
 * @Description:
 * @Date: 上午 10:51 2017/7/17 0017
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;

    private final String VIEW_PATH = "/html/product/";
    private static String ROOT = "D:/bootTestUpload/";
    private final ResourceLoader resourceLoader;

    public ProductController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = "/toCreateOrUpdateProduct")
    public ModelAndView toCreateOrUpdateProductType(){
        ModelAndView mv = new ModelAndView();
        List<ProductType> productTypeList = productTypeService.findAllProductTypeList();
        // 返回给页面的所有参数
        returnMap = new HashMap<String, Object>();
        returnMap.put("productTypeList", productTypeList);
        mv.addAllObjects(returnMap);
        mv.setViewName(VIEW_PATH + "product_create_or_update");
        return mv;
    }

    @RequestMapping(value = "/createProduct")
    public void createProduct(@RequestParam MultipartFile uploadFile){
        if(!uploadFile.isEmpty()){
            try {
                FileUtil.uploadFile(ROOT, uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
