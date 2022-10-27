/**
 * @(#)DemoApplication.java, Oct 24, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.data.*;
import com.nus.cs5424.storage.*;
import com.nus.cs5424.storage.db.WarehouseStorageImpl;
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
    WarehouseStorage warehouseStorage;

    @Autowired
    DistrictStorage districtStorage;

    @Autowired
    CustomerStorage customerStorage;

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
//
//        orderLineStorage.add(orderLine);


//////2.2 Payment Transaction
//////inputs:
//        int c_w_id = 5;
//        int c_d_id = 1;
//        int c_id = 2;
//        int payment = 10000;
////        warehouseStorage.updateW_YTDByPayment(c_w_id, payment);
////        districtStorage.updateD_YTDByPayment(c_w_id, c_d_id, payment);
////        customerStorage.updateByPayment(c_w_id, c_d_id, c_id, payment);
////
//////outputs:
//        Customer c = customerStorage.getCustomerByIdentifier(c_w_id, c_d_id, c_id);
//        System.out.println("Customer's Identifier: " + c.getC_w_id() + ", " + c.getC_d_id() + + c.getC_id() + "\tName: "
//                + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last() + "\tAddress: "
//                + c.getC_street_1() + ", " + c.getC_street_2() + ", " + c.getC_city() + ", " + c.getC_state() + ", " + c.getC_zip()
//                + "\tC_PHONE: " + c.getC_phone() + "\tC_SINCE: " + c.getC_since() + "\tC_CREDIT: " + c.getC_credit()
//                + "\tC_CREDIT_LIM: " + c.getC_credit_lim() + "\tC_DISCOUNT: " + c.getC_discount() + "\tC_BALANCE: " + c.getC_balance());
//        Warehouse w = warehouseStorage.getWarehouseByIdentifier(c_w_id);
//        System.out.println("Warehouse address: " + w.getW_street_1() + ", " + w.getW_street_2() + ", " + w.getW_city() + ", " + w.getW_state() + ", " + w.getW_zip());
//        District d = districtStorage.getDistrictByIdentifier(c_w_id, c_d_id);
//        System.out.println("District address: " + d.getD_street_1() + ", " + d.getD_street_2() + ", " + d.getD_city() + ", " + d.getD_state() + ", " + d.getD_zip());

    }
}