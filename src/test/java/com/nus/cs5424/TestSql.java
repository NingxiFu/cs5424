/**
 * @(#)TestSql.java, Oct 27, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.data.Stock;
import com.nus.cs5424.storage.StockStorage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author guochenghui
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class TestSql {

    @Autowired
    StockStorage stockStorage;

    @Test
    void stock() {
        Stock query = stockStorage.query(1, 1);
        System.out.println(query.toString());
    }

}