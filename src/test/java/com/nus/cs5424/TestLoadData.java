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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author guochenghui
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLoadData {

    @Test
    public void testRead() throws FileNotFoundException {
//        driver.multiThread();
//        driver.doTransactions(2);
//        Driver driver = new Driver("aaaa");
//        driver.doTransactions(2);

//        BeanC beanC1 = ApplicationContext.
    }

    @Test
    public void getBean() {
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext(TestLoadData.class);
        Driver driver = (Driver) SpringBeanUtil.getBean("driver");
        Driver driver1 = (Driver) SpringBeanUtil.getBean("driver");

        System.out.println(driver);
        System.out.println(driver1);

//
    }

    @Test
    public void clients() throws InterruptedException, ExecutionException {
        AnnotationConfigApplicationContext annotationContext = new AnnotationConfigApplicationContext(TestLoadData.class);
        Driver driver = (Driver) SpringBeanUtil.getBean("driver");
        Driver driver1 = (Driver) SpringBeanUtil.getBean("driver");

        driver.setIndex(1);
        driver1.setIndex(2);

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        List<Callable<Double>> callableList = new ArrayList<>(2);

        callableList.add(driver);
        callableList.add(driver1);

        List<Future<Double>> futureList = executorService.invokeAll(callableList);

        for (Future<Double> future : futureList) {
            future.get();
        }
    }

    @Test
    public void test1() throws InterruptedException, ExecutionException {
        int CLIENT = 20;

        System.out.println("BEGIN BENCHMARK");

        ExecutorService executorService = Executors.newFixedThreadPool(CLIENT);
        List<Callable<Double>> callableList = new ArrayList<>(CLIENT);


        for (int i = 0; i < CLIENT; i++) {
            Driver driver = (Driver) SpringBeanUtil.getBean("driver");
            driver.setIndex(i);
        }

        List<Future<Double>> futureList = executorService.invokeAll(callableList);

        for (Future<Double> future : futureList) {
            future.get();
        }
    }
}