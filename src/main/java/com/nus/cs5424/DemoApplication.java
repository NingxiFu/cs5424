/**
 * @(#)DemoApplication.java, Oct 24, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.data.OrderLine;
import com.nus.cs5424.data.Stock;
import com.nus.cs5424.storage.DistrictStorage;
import com.nus.cs5424.storage.OrderLineStorage;
import com.nus.cs5424.storage.OrderStorage;
import com.nus.cs5424.storage.StockStorage;
import com.nus.cs5424.storage.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

/**
 * @author guochenghui
 */
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    DistrictStorage districtStorage;

    @Autowired
    OrderStorage orderStorage;

    @Autowired
    StockStorage stockStorage;

    @Autowired
    OrderLineStorage orderLineStorage;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        System.out.println("Connected to the YugabyteDB server successfully.");
//        jdbcTemplate.execute("DROP TABLE IF EXISTS employee");
//        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS employee" +
//                "  (id text primary key, name varchar, email varchar)");
//        System.out.println("Created table employee");
//
//
//        Employee customer = new Employee("sl1",
//                "User One",
//                "user@one.com");
//
//        customerRepository.save(customer);
//
//        Employee customerFromDB = null;
//        customerFromDB = customerRepository.findByEmail("user@one.com");
//
//        System.out.println(String.format("Query returned: name = %s, email = %s",
//                customerFromDB.getName(), customerFromDB.getEmail()));
//            Order order = new Order();
//            order.setId(10);
//            order.setO_d_id(10);
//            order.setO_id(1000000);
//            order.setO_c_id(22);
//
//            order.setO_entry_d(new Timestamp(System.currentTimeMillis()));
//            order.setO_ol_cnt(new BigDecimal(1));
//            order.setO_all_local(new BigDecimal(1));
//
//            orderStorage.add(order);
//        Stock query = stockStorage.query(1, 1);
//        System.out.println(query);
//        OrderLine orderLine = new OrderLine();
//        orderLine.setId(1);
//        orderLine.setOl_d_id(1);
//        orderLine.setOl_o_id(1);
//        orderLine.setOl_number(9999999);
//        orderLine.setOl_i_id(1);
////        orderLine.setOl_delivery_d();
//        orderLine.setOl_amount(new BigDecimal(1));
//        orderLine.setOl_supply_w_id(1);
//        orderLine.setOl_quantity(new BigDecimal(1));
//        orderLine.setOl_dist_info("aaaa");

//        orderLineStorage.add(orderLine);
    }
}