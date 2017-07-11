package com.annie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 16:08 2017/6/28 0028
 */

@RestController
@RequestMapping("/abc")
public class TestRestController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView toIndex(Map<String,Object> map) {
        map.put("hello", "modelAndView");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        mv.addAllObjects(map);
        return mv;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> test() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("hello", "modelAndView");
        return map;
    }
}
