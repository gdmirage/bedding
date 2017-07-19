package com.annie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 16:46 2017/7/13 0013
 */
public class BaseController{

    public Map<String, Object> returnMap;

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 设置分页参数返回给页面
     * @param request
     * @return
     */
    public Map<String, Object> setPaginatorParams(HttpServletRequest request){
        returnMap.put("requestPath", request.getServletPath());
        return returnMap;
    }
}
