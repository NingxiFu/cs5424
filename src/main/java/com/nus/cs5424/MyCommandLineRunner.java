/**
 * @(#)MyCommandLineRunner.java, 11月 04, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.driver.Driver;
import com.nus.cs5424.util.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author guochenghui
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private static final int CLIENT = 20;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("BEGIN BENCHMARK");

        ExecutorService executorService = Executors.newFixedThreadPool(CLIENT);
        List<Callable<Double>> callableList = new ArrayList<>(CLIENT);

        for (int i = 0; i < CLIENT; i++) {
            Driver driver = (Driver) SpringBeanUtil.getBean("driver");
            driver.setIndex(i);
            callableList.add(driver);
        }

        List<Future<Double>> futureList = executorService.invokeAll(callableList);

        for (Future<Double> future : futureList) {
            future.get();
        }

        System.out.println("END BENCHMARK");
    }
}