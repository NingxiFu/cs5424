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
import org.springframework.context.annotation.Scope;

import java.io.BufferedOutputStream;
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
import java.util.concurrent.Callable;


import com.google.common.math.Quantiles;
import com.google.common.math.Stats;
import org.springframework.stereotype.Service;

/**
 * @author guochenghui
 */
@Service
@Scope("prototype")
public class Driver implements Callable<Double> {

    private static final String tx_file = "project_files/xact_files/%s.txt";
    private static final String benchMark = "benchMark{%s}.txt";
    private static final String LOG = "LogFile{%s}.txt";

    public int index = 0;

    public void setIndex(int index) {
        this.index = index;
    }

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

    public double doTransactions(int index) throws FileNotFoundException {
        String threadName = Thread.currentThread().getName();
        long BEGIN_THREAD = System.currentTimeMillis();

        String readFile = String.format(tx_file, index);
        String writeFile = String.format(benchMark, index);
        String logFile = String.format(LOG, index);

        //-----------------------------
        FileOutputStream fos = new FileOutputStream(logFile);
        PrintStream stream = new PrintStream(new BufferedOutputStream(fos));
        ((ThreadPrintStream)System.out).setThreadOut(stream);

        System.out.println("---------------" + threadName + "Thread Begin---------------");
        System.out.println(String.format("ReadFile Name %s", readFile));
        System.out.println(String.format("WriteFile Name %s", writeFile));
        System.out.println(String.format("LogFile Name %s", logFile));


        Scanner sc = null;
        try {
            sc = new Scanner(new File(readFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long totalTime = 0;
        List<Long> transactionTimeList = new ArrayList<>();
        long res = 0;

        while (sc.hasNext()) {
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
                for (String arg : args) {
                    argsList.add(arg);
                }
                argsList.add(csv);
                args = argsList.toArray(new String[argsList.size()]);
            }

            // BEGIN TRANSACTION
            long start = System.currentTimeMillis();
            try {
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

            } catch (Exception e) {
                e.printStackTrace();
            }
            // END TRANSACTION

            res++;
            long end = System.currentTimeMillis();
            transactionTimeList.add(end - start);
            totalTime += (end - start);

            // TEST
            System.out.println("事务类型： " + type + " 所花费的时间： " + (end - start));
            if (res % 10 == 0)
                System.out.println("Thread:" + threadName + "res : " + res + " time :" + (end - BEGIN_THREAD));
            if (res == 10)
                break;
        }
        System.out.close();

        System.out.println("---------------" + threadName + "Transaction Done---------------");

        Collections.sort(transactionTimeList);
        double mean = Stats.meanOf(transactionTimeList);
        double median = Quantiles.median().compute(transactionTimeList);
        double percentile95 = Quantiles.percentiles().index(95).compute(transactionTimeList);
        double percentile99 = Quantiles.percentiles().index(99).compute(transactionTimeList);
        Double execTimeSec = (double) totalTime / 1000.0;


        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(writeFile));
            out.write("Total Number of transactions processed: " + res + "\n");
            out.write("Total elapsed time for processing the transactions: " + execTimeSec + "\n");
            out.write("Transaction throughput: " + (double) res / (double) execTimeSec + "\n");
            out.write("Average transaction latency: " + mean + "\n");
            out.write("Median transaction latency: " + median + "\n");
            out.write("95th percentile transaction latency: " + percentile95 + "\n");
            out.write("99th percentile transaction latency: " + percentile99 + "\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ((double) res / (double) execTimeSec);
    }

    @Override
    public Double call() throws Exception {
        return doTransactions(this.index);
    }

    private void setOut(String filenNameEnd, boolean isOut) throws FileNotFoundException {
        File file;
        if(isOut){
            file = new File(filenNameEnd);}
        else{
            file = new File(filenNameEnd);}

        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        ((ThreadPrintStream)System.out).setThreadOut(ps);
    }
}