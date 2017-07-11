package com.annie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 16:08 2017/6/28 0028
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String toIndex(Map<String,Object> map) {
        map.put("hello", "bad boy");
        return "/html/index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> test(Map<String,Object> map) {
        map.put("hello", "modelAndView");
        return map;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String toProductList(Map<String,Object> map) {
        map.put("hello", "bad boy");
        return "/html/product/product_list";
    }
}
