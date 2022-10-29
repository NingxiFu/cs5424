/**
 * @(#)TestSql.java, Oct 27, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424;

import com.nus.cs5424.data.*;
import com.nus.cs5424.storage.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.List;

/**
 * @author guochenghui
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class TestSql {

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

    @Test
    void stock() {
        Stock query = stockStorage.query(1, 1);
        System.out.println(query.toString());
    }

    @Test
    void payment(){
//2.2 Payment Transaction
//inputs:
        int c_w_id = 5;
        int c_d_id = 1;
        int c_id = 2;
        int payment = 10000;
//transaction:
//        warehouseStorage.updateW_YTDByPayment(c_w_id, payment);
//        districtStorage.updateD_YTDByPayment(c_w_id, c_d_id, payment);
//        customerStorage.updateByPayment(c_w_id, c_d_id, c_id, payment);
//outputs:
        Customer c = customerStorage.getCustomerByIdentifier(c_w_id, c_d_id, c_id);
        System.out.println("Customer's Identifier: " + c.getC_w_id() + ", " + c.getC_d_id() + + c.getC_id() + ",\tName: "
                + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last() + ",\tAddress: "
                + c.getC_street_1() + ", " + c.getC_street_2() + ", " + c.getC_city() + ", " + c.getC_state() + ", " + c.getC_zip()
                + ",\tPhone: " + c.getC_phone() + ",\tSince: " + c.getC_since() + ",\tCredit: " + c.getC_credit()
                + ",\tCredit limit: " + c.getC_credit_lim() + ",\tDiscount: " + c.getC_discount() + ",\tBalance: " + c.getC_balance());
        Warehouse w = warehouseStorage.getWarehouseByIdentifier(c_w_id);
        System.out.println("Warehouse address: " + w.getW_street_1() + ", " + w.getW_street_2() + ", " + w.getW_city() + ", " + w.getW_state() + ", " + w.getW_zip());
        District d = districtStorage.getDistrictByIdentifier(c_w_id, c_d_id);
        System.out.println("District address: " + d.getD_street_1() + ", " + d.getD_street_2() + ", " + d.getD_city() + ", " + d.getD_state() + ", " + d.getD_zip());
    }

    @Test
    void delivery() {
//2.3 Delivery Transaction
//inputs:
        int w_id = 5;
        int carrier_id = 8;
//transaction:
        for (int d_id = 1; d_id <= 10; d_id++){ //when debugging, can change to d_id <= 1.
            Order o = orderStorage.getOldestOrderByDistrict(w_id, d_id);
            int o_id = o.getO_id();
            int c_id = o.getO_c_id();
//            System.out.println(o_id + " " + c_id);

            orderStorage.updateCarrierIdByOldestOrder(w_id, d_id, o_id, carrier_id);
            orderLineStorage.updateDelivery_DByOneOrder(w_id, d_id, o_id);
            int ol_amount_sum = orderLineStorage.getSumOfAmountByOneOrder(w_id, d_id, o_id);
            customerStorage.updateByDelivery(w_id, d_id, c_id, ol_amount_sum);
        }
//outputs:None
    }


    @Test
    void orderStatus(){
//2.4 Order-Status Transaction
//inputs:
        int c_w_id = 5;
        int c_d_id = 1;
        int c_id = 67;
//transaction:
        Customer c = customerStorage.getCustomerByIdentifier(c_w_id, c_d_id, c_id);
        Order o = orderStorage.getLastOrderByCustomer(c_w_id, c_d_id, c_id);
        List<OrderLine> ols = orderLineStorage.getOrderLinesByOneOrder(o.getO_w_id(), o.getO_d_id(), o.getO_id());
//outputs:
        System.out.println("Customer's Name: " + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last() +
                ",\tBalance: " + c.getC_balance());
        System.out.println("Customer's Last Order ---- Order number: " + o.getO_id() + ",\tEntry date and time: " + o.getO_entry_d() + ",\tCarrier identifier: " + o.getO_carrier_id());
        for (OrderLine ol : ols){
            System.out.println("Item number: " + ol.getOl_i_id() + ",\tSupplying warehouse number: " + ol.getOl_supply_w_id() + ",\tQuantity ordered: "
                    + ol.getOl_quantity() + ",\tTotal price for ordered item: " + ol.getOl_amount() + ",\tData and time of delivery: " + ol.getOl_delivery_d());
        }
    }


}