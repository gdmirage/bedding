package com.annie.controller;

import com.annie.config.AnnieProperties;
import com.annie.constant.Constant;
import com.annie.entity.ProductType;
import com.annie.service.ProductTypeService;
import com.annie.utils.FileUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class ProductController extends BaseController {

    @Resource(name = "productTypeService")
    private ProductTypeService productTypeService;
    @Autowired
    private AnnieProperties annieProperties;
    private final String VIEW_PATH = "/html/product/";


    @RequestMapping(value = "/toCreateOrUpdateProduct")
    public ModelAndView toCreateOrUpdateProductType() {
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
    public void createProduct(@RequestParam MultipartFile uploadFile, String productContent,  String productName) {
        logger.info("进来了");
        logger.info("productContent==="+productContent);
        logger.info("productName==="+productName);
        if (!uploadFile.isEmpty()) {
            try {
                FileUtil.uploadFile(annieProperties.getFilePath()+ Constant.IMG_FILE_PATH, uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
