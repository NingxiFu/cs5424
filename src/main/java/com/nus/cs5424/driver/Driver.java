/**
 * @(#)Driver.java, Oct 29, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.driver;

import com.nus.cs5424.txs.*;
import com.nus.cs5424.util.ThreadPrintStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.google.common.math.Quantiles;
import com.google.common.math.Stats;

/**
 * @author guochenghui
 */
@Service
public class Driver {

    private static String outFolder = "output/transactions/";
    private static String errFolder = "output/performance/";
    private static int index = 1;

    @Autowired
    NewOrder newOrder;

    @Autowired
    Payment payment_t;

    @Autowired
    Delivery delivery_t;

    @Autowired
    OrderStatus orderStatus_t;

    @Autowired
    StockLevel stockLevel_t;

    @Autowired
    PopularItem popularItem_t;

    @Autowired
    TopBalance topBalance_t;

    @Autowired
    RelatedCustomer relatedCustomer_t;

    private static final String tx_file = "project_files/xact_files/testBenchMark.txt";

    public long doTransactions() throws FileNotFoundException {
        // 读取文件
        // 根据对应的标识调用对应的tx
        // 执行成功返回 + 1
        Scanner sc = null;
        try {
            sc = new Scanner(new File(tx_file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long totalTime = 0;
        List<Long> transactionTimeList = new ArrayList<>();
        long res = 0;

        while(sc.hasNext()){
            String[] args = sc.nextLine().split(",");
            String type = args[0];

            if (type.equals("N")) {
                int n = Integer.parseInt(args[args.length - 1]);
                List<String> orderLines = new ArrayList<String>();
                for (int i = 0; i < n; i++) {
                    orderLines.add(sc.nextLine());
                }
                String csv = String.join("\n", orderLines);

                List<String> argsList = new ArrayList<String>();
                for (String arg: args) {
                    argsList.add(arg);
                }
                argsList.add(csv);
                args = argsList.toArray(new String[argsList.size()]);
            }

            // TODO: 这里开始计算
            long start = System.currentTimeMillis();

            switch (type) {
                case "N":
                    newOrder.process(args);
                    break;
                case "P":
                    payment_t.process(args);
                    break;
                case "D":
                    delivery_t.process(args);
                    break;
                case "O":
                    orderStatus_t.process(args);
                    break;
                case "S":
                    stockLevel_t.process(args);
                    break;
                case "I":
                    popularItem_t.process(args);
                    break;
                case "T":
                    topBalance_t.process(args);
                    break;
                case "R":
                    relatedCustomer_t.process(args);
                    break;
                default:
                    System.out.println("没有找到匹配的tx");
            }

            long end = System.currentTimeMillis();

            res++;
            transactionTimeList.add(end - start);
            totalTime += (end - start);

            // TEST
//            if(res % 10 == 0) System.out.println("res : " + res + " time :" + (System.currentTimeMillis() - start));
        }

        Collections.sort(transactionTimeList);
        double mean = Stats.meanOf(transactionTimeList);
        double median = Quantiles.median().compute(transactionTimeList);
        double percentile95 = Quantiles.percentiles().index(95).compute(transactionTimeList);
        double percentile99 = Quantiles.percentiles().index(99).compute(transactionTimeList);
        Double execTimeSec = (double) totalTime / 1000.0;


        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("benchMark.txt"));
            out.write("Total Number of transactions processed: "+ res +"\n");
            out.write("Total elapsed time for processing the transactions: " + execTimeSec + "\n");
            out.write("Transaction throughput: " + (double) res / (double) execTimeSec +"\n");
            out.write("Average transaction latency: " + mean +"\n");
            out.write("Median transaction latency: " + median + "\n");
            out.write("95th percentile transaction latency: " + percentile95 + "\n");
            out.write("99th percentile transaction latency: " + percentile99 + "\n");
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // output
//        System.out.close();
//        this.setOut("_stderr.txt", false);
//        System.out.println("Total Number of transactions processed: "+ res);
//        System.out.println("Total elapsed time for processing the transactions: " + execTimeSec);
//        System.out.println("Transaction throughput: " + (double) res / (double) execTimeSec);
//        System.out.println("Average transaction latency: " + mean);
//        System.out.println("Median transaction latency: " + median);
//        System.out.println("95th percentile transaction latency: " + percentile95);
//        System.out.println("99th percentile transaction latency: " + percentile99);

        return res;
    }

    private void setOut(String filenNameEnd, boolean isOut) throws FileNotFoundException {
        File file;
        if(isOut){
            file = new File(outFolder+index+filenNameEnd);}
        else{
            file = new File(errFolder+index+filenNameEnd);}

        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        ((ThreadPrintStream)System.out).setThreadOut(ps);
    }

}