/**
 * @(#)TestLoadData.java, Oct 29, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.driver.Driver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guochenghui
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestOneTransaction {

    @Autowired
    Driver driver;

    @Test
    public void testRead(){
        driver.doTransactions(2);
    }
}