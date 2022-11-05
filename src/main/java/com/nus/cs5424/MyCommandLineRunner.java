/**
 * @(#)MyCommandLineRunner.java, 11月 04, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author guochenghui
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

//    @Autowired
//    Driver driver;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("hello world");

//        driver.multiThread();

    }
}