///**
// * @(#)TestFile.java, Nov 04, 2022.
// * <p>
// * Copyright 2022 fenbi.com. All rights reserved.
// * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.nus.cs5424;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
///**
// * @author guochenghui
// */
//public class TestFile {
//
//    @Test
//    public void writeOut(){
//        BufferedWriter out = null;
//        try {
//            out = new BufferedWriter(new FileWriter("runoob.txt"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            out.write("菜鸟教程");
//            out.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}