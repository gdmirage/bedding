package com.annie.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: Blade
 * @Description:
 * @Date: 上午 11:21 2017/7/18 0018
 */
//@ConfigurationProperties(locations = "classpath:annie.properties")
@Configuration
@PropertySource("classpath:annie.properties")
@Component
public class AnnieProperties {

    @Value("${annie.file.path}")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
