///**
// * @(#)TestSout.java, 11月 06, 2022.
// * <p>
// * Copyright 2022 fenbi.com. All rights reserved.
// * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.nus.cs5424;
//
//import com.nus.cs5424.util.ThreadPrintStream;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @author guochenghui
// */
//public class TestSout {
//
//    @Test
//    public void testMultiThread(){
//        ExecutorService es = Executors.newFixedThreadPool(4);
//        ThreadPrintStream.replaceSystemOut();
//        for (int i = 0; i < 6; i++) {
//            int finalI = i;
//            int finalI1 = i;
//            es.submit(() -> {
//                try {
//                    test(finalI1);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    throw new RuntimeException(e);
//                }
//                System.out.println("hello world");});
//        }
//
//
//        ThreadPrintStream.replaceSystemOut();
//
//    }
//
//    @Test
//    public void test(int index) throws FileNotFoundException {
//        //-----------------------------
//        // Create a text file where System.out.println()
//        // will send its data for this thread.
//        String name = Thread.currentThread().getName();
//        FileOutputStream fos = new FileOutputStream(index + ".txt");
//
//        // Create a PrintStream that will write to the new file.
//        PrintStream stream = new PrintStream(new BufferedOutputStream(fos));
//
//        // Install the PrintStream to be used as System.out for this thread.
//        ((ThreadPrintStream)System.out).setThreadOut(stream);
//
//        // Output three messages to System.out.
//        System.out.println(name + ": first message");
//        System.out.println("This is the second message from " + name);
//        System.out.println(name + ": 3rd message");
//
//        // Close System.out for this thread which will
//        // flush and close this thread's text file.
//        System.out.close();
//    }
//
//}