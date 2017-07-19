package com.annie.controller;

import com.annie.config.AnnieProperties;
import com.annie.constant.Constant;
import com.annie.utils.FileUtil;
import com.annie.utils.ueditor.ActionEnter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 16:08 2017/6/28 0028
 */

@RestController
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private AnnieProperties annieProperties;

    private static final String UEDITOR_CONFIG_JSON_PATH = "/static/js/plugins/ueditor/jsp/";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView toIndex(Map<String, Object> map) {
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
            return ResponseEntity.ok(FileUtil.getFileResource(annieProperties.getFilePath() + Constant.IMG_FILE_PATH, filename));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/ueditorUploadConfig")
    @ResponseBody
    public String ueditorUploadConfig(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String rootPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            logger.info("rootPath===" + rootPath);
            String action = request.getParameter("action");
            if ("catchimage".equals(action)) {
                return "";
            }
            String res = new ActionEnter(request, rootPath + UEDITOR_CONFIG_JSON_PATH).exec();
            logger.info("res====" + res);
            return res;
        } catch (JSONException e) {
            logger.error("", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
        return "";
    }

//    @RequestMapping(value = "/uploadimage")
//    public void uploadimage(@RequestParam MultipartFile upfile) {
//        logger.info("进来了");
//        if (!upfile.isEmpty()) {
//            try {
//                FileUtil.uploadFile(annieProperties.getFilePath() + Constant.IMG_FILE_PATH, upfile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
