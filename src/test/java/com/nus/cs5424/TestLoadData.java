/**
 * @(#)TestLoadData.java, Oct 29, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.driver.Driver;
import com.nus.cs5424.util.SpringBeanUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;

/**
 * @author guochenghui
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLoadData {

//    @Autowired
//    Driver driver;

//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Test
    public void testRead() throws FileNotFoundException {
//        driver.multiThread();
//        driver.doTransactions(2);
//        Driver driver = new Driver("aaaa");
//        driver.doTransactions(2);

//        BeanC beanC1 = ApplicationContext.
    }

    @Test
    public void getBean(){
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext(TestLoadData.class);
        Driver driver = (Driver)SpringBeanUtil.getBean("driver");
        Driver driver1 = (Driver)SpringBeanUtil.getBean("driver");

        System.out.println(driver);
        System.out.println(driver1);

//
    }
}