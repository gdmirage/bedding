package com.annie.controller;

import com.annie.config.AnnieProperties;
import com.annie.constant.Constant;
import com.annie.entity.Product;
import com.annie.entity.ProductType;
import com.annie.service.ProductService;
import com.annie.service.ProductTypeService;
import com.annie.utils.FileUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource(name = "productService")
    private ProductService productService;
    @Autowired
    private AnnieProperties annieProperties;
    private final String VIEW_PATH = "/html/product/";

    @RequestMapping(value = "/findProductPage")
    public ModelAndView findProductPage(@RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE) Integer pageSize,
                                            @RequestParam(value = "pageNum", defaultValue = Constant.PAGE_NUM) Integer pageNum,
                                            HttpServletRequest request) {
        PageInfo<Product> productPageInfo = productService.findProductPage(null, pageSize, pageNum);

        // 返回给页面的所有参数
        returnMap = new HashMap<String, Object>();
        returnMap = setPaginatorParams(request);

        returnMap.put("productPageInfo", productPageInfo);

        ModelAndView mv = new ModelAndView();
        mv.addAllObjects(returnMap);
        mv.setViewName(VIEW_PATH + "product_list");
        return mv;
    }

    @RequestMapping(value = "/toCreateOrUpdateProduct")
    public ModelAndView toCreateOrUpdateProduct() {
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
    public void createProduct(MultipartFile uploadFile,
                              String productContent,
                              String productName,
                              Integer productTypeId,
                              String productUnit) {
        String fileName = null;
        if (!uploadFile.isEmpty()) {
            try {
                fileName = FileUtil.uploadFile(annieProperties.getFilePath()+ Constant.IMG_FILE_PATH, uploadFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setProductTypeId(productTypeId);
        product.setProductContent(productContent);
        product.setProductUnit(productUnit);
        product.setProductMainPic(fileName);
    }
}
