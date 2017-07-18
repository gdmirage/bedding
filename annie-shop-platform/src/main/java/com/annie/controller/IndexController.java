package com.annie.controller;

import com.annie.config.AnnieProperties;
import com.annie.constant.Constant;
import com.annie.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 16:08 2017/6/28 0028
 */

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private AnnieProperties annieProperties;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView toIndex(Map<String,Object> map) {
        map.put("hello", "modelAndView");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/html/index");
        mv.addAllObjects(map);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(FileUtil.getFileResource(annieProperties.getFilePath()+ Constant.IMG_FILE_PATH, filename));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
