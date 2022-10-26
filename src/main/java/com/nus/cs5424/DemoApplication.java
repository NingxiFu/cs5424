/**
 * @(#)DemoApplication.java, Oct 24, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.data.Employee;
import com.nus.cs5424.data.Warehouse;
import com.nus.cs5424.storage.EmployeeRepository;
import com.nus.cs5424.storage.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

/**
 * @author guochenghui
 */
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EmployeeRepository customerRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

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

        Optional<Warehouse> byId = warehouseRepository.findById(3);
        System.out.println(byId.get().toString());

    }
}