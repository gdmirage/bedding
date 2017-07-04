package com.annie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: Blade
 * @Description: 让spring boot 扫描自定义的静态文件路径
 * @Date: 下午 15:25 2017/6/30 0030
 */

@Configuration
public class StaticResourcePathConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry){
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }
}
