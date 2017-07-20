package com.annie.service;

import com.annie.dao.ProductMapper;
import com.annie.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: Blade
 * @Description:
 * @Date: 下午 18:07 2017/7/6 0006
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class DAOTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test() {
        System.out.println(productMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void create() {
        Product p = new Product();
        p.setProductName("abcde");
        productMapper.insertProduct(p);
    }

}
