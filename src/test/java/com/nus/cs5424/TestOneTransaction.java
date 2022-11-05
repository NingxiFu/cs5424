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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author guochenghui
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestOneTransaction {

    @Autowired
    Driver driver;
    @Autowired


    @Test
    public void testRead(){
        driver.doTransactions(0);
    }

    @Test
    public void test1() throws InterruptedException, ExecutionException {
        driver.doTransactions(1);//2192548.92
    }
}