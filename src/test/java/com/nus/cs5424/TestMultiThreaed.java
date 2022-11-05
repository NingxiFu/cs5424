///**
// * @(#)TestMultiThreaed.java, 11月 05, 2022.
// * <p>
// * Copyright 2022 fenbi.com. All rights reserved.
// * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.nus.cs5424;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author guochenghui
// */
//public class TestMultiThreaed {
//
//    @Test
//    public void run(){
//        ExecutorService es = Executors.newFixedThreadPool(4);
//        for (int i = 0; i < 6; i++) {
//            es.submit(() -> {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("hello world");});
//        }
//
//        try {//等待直到所有任务完成
//            es.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        es.shutdown();
//    }
//
//}