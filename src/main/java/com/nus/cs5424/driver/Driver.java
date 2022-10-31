/**
 * @(#)Driver.java, Oct 29, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.driver;

import com.nus.cs5424.txs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author guochenghui
 */
@Service
public class Driver {

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

    private static final String tx_file = "/Users/funingxi/Documents/_NUS_STUDY/sem2/CS5424 Distributed Database/Project/project_files/xact_files/0.txt";

    public long doTransactions(){
        // 读取文件
        // 根据对应的标识调用对应的tx
        // 执行成功返回 + 1
        Scanner sc = null;
        try {
            sc = new Scanner(new File(tx_file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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

            for (String s : args) System.out.print(s + " ");
            System.out.println();

            // TODO: 根据tx选择对应的内容
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


            res++;
        }

        return res;
    }

}