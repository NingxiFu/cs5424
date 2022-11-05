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
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
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
//    @Test
//    public void count(){
//        String folder = "project_files/xact_files/%s.txt";
//
//        for (int j = 0; j < 20; j++) {
//            String fileName = String.format(folder, j);
//            int count = 0;
//            // Read Files
//            Scanner sc = null;
//            try {
//                sc = new Scanner(new File(fileName));
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//
//            while (sc.hasNext()) {
//                String[] args = sc.nextLine().split(",");
//                String type = args[0];
//
//                if (type.equals("N")) {
//                    int n = Integer.parseInt(args[args.length - 1]);
//                    List<String> orderLines = new ArrayList<String>();
//                    for (int i = 0; i < n; i++) {
//                        orderLines.add(sc.nextLine());
//                    }
//                    String csv = String.join("\n", orderLines);
//
//                    List<String> argsList = new ArrayList<String>();
//                    for (String arg : args) {
//                        argsList.add(arg);
//                    }
//                    argsList.add(csv);
//                    args = argsList.toArray(new String[argsList.size()]);
//                }
//
//                count++;
//            }
//
//            System.out.println(count);
//        }
//    }
//
//}