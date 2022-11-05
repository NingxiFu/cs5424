/**
 * @(#)TestFileName.java, 11月 05, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author guochenghui
 */
public class TestFileName {

    private static final String tx_file = "project_files/xact_files/%s.txt";
    private static final String benchMark = "benchMark{%s}.txt";


    @Test
    public void name(){
        String s = String.format(tx_file, 1);
        System.out.println(s);
    }

    @Test
    public void name1(){
        String s = String.format(benchMark, 1);
        System.out.println(s);
    }

}