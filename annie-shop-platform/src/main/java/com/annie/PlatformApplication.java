package com.annie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 16:10 2017/6/28 0028
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.annie"})
@MapperScan(basePackages = {"com.annie.dao"})
public class PlatformApplication {

    public static void main(String args[]){
        SpringApplication.run(PlatformApplication.class, args);
    }
}
