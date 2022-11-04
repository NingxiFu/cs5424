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
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    ItemStorage itemStorage;



    @Test
    void stock() {
        Stock query = stockStorage.query(1, 1);
        System.out.println(query.toString());
    }

    @Test
    void district(){
        districtStorage.updateNext_O_ID(1,3, 3002);
    }

    @Test
    void payment(){
//2.2 Payment Transaction
//inputs:
        int c_w_id = 5;
        int c_d_id = 1;
        int c_id = 2;
        BigDecimal payment = BigDecimal.valueOf(10000);
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
            BigDecimal ol_amount_sum = orderLineStorage.getSumOfAmountByOneOrder(w_id, d_id, o_id);
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

    @Test
    void stockLevel(){
//2.5 Stock-level Transaction
//inputs:
        int w_id = 5;
        int d_id = 1;
        int threshold = 72;
        int num_last_orders = 1;
//transaction:
        int next_o_id = districtStorage.getNext_O_IDByPrimaryKey(w_id, d_id);
        List<Integer> item_ids = orderLineStorage.getItemIdsByLastOrders(w_id, d_id, next_o_id, num_last_orders);
        int num_of_i = stockStorage.getNumOfItemBelowThresholdByWarehouse(w_id, item_ids, threshold);
//outputs:
        System.out.println("Total number of items where stock below threshold: " + num_of_i);
    }

    @Test
    void popularItem(){
//2.6 Popular-Item Transaction
//inputs:
        int w_id = 5;
        int d_id = 1;
        int num_last_orders = 4;
//transaction:
        System.out.println("District identifier: " + w_id + "," + d_id);
        System.out.println("Number of last orders to be examined: " + num_last_orders);
        int next_o_id = districtStorage.getNext_O_IDByPrimaryKey(w_id, d_id);
        Map items = new HashMap<Integer, String>();
        for (int o_id = next_o_id - num_last_orders; o_id < next_o_id; o_id++){
            Order o = orderStorage.getLastOrderByIdentifier(w_id, d_id, o_id);
            System.out.println("\nOrder number: " + o_id);
            System.out.println("Entry date and time: " + o.getO_entry_d());
            Customer c = customerStorage.getCustomerByIdentifier(w_id, d_id, o.getO_c_id());
            System.out.println("Customer name: " + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last());
            List<OrderLine> ols = orderLineStorage.getOrderlinesByPopularItemsInOneOrder(w_id, d_id, o_id);
            for (OrderLine ol : ols){
                Integer i_id = ol.getOl_i_id();
                Item i = itemStorage.query(i_id);
                items.put(i.getI_id(), i.getI_name());
                System.out.println("Item name: " + items.get(i_id) + "\t\tQuantity ordered: " + ol.getOl_quantity());
            }
        }

        System.out.println("\nThe percentage of examined orders that contain each popular item:");
        for (Object i_id : items.keySet()){
            int count = orderLineStorage.getCountByLastOrdersContainsTheItem(w_id, d_id, Integer.parseInt(i_id.toString()), next_o_id, num_last_orders);
            System.out.println("Item name: " + items.get(i_id)  + "\tPercentage: " + String.format("%.2f", (float)count*100/num_last_orders) + "%");
        }
    }

    @Test
    void topBalance(){
//2.7 Top-Balance Transaction
//inputs:None
//transaction:
        List<Customer> cs = customerStorage.getCustomersByTopBalance();
//outputs:
        for (Customer c : cs){
            System.out.println("Customer's Name: " + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last() + ",\tBalance: " + c.getC_balance());
            System.out.println("Warehouse's Name: " + warehouseStorage.getWarehouseByIdentifier(c.getC_w_id()).getW_name());
            System.out.println("District's Name: " + districtStorage.getDistrictByIdentifier(c.getC_w_id(), c.getC_d_id()).getD_name());
        }
    }
    @Test
    void relatedCustomer(){
//2.8 Related-Customer Transaction
//inputs:
        int given_w_id = 1;
        int given_d_id = 9;
        int given_c_id = 37;
//transaction:
        Set<Integer> given_i_id_l = new HashSet<>();
        List<String> c_identifiers = new ArrayList<String>();
        List<Integer> given_o_id_l = orderStorage.getAllOrderIdByCustomer(given_w_id, given_d_id, given_c_id);
        for(int given_o_id: given_o_id_l){
            List<OrderLine> given_ol_l = orderLineStorage.getOrderLinesByOneOrder(given_w_id, given_d_id, given_o_id);
            for (OrderLine given_ol: given_ol_l){
                given_i_id_l.add(given_ol.getOl_i_id());
//                System.out.println("\ti_id() = "+given_ol.getOl_i_id());
            }
        }
        for (int w_id = 1; w_id <= 10; w_id++){
//            System.out.println("\n\n\nw_id = "+w_id);
            if (w_id == given_w_id){
                continue;
            }
            for (int d_id = 1; d_id <= 10; d_id++){
//                System.out.println("d_id = "+d_id);
                List<Integer> contain_items_o_id_l = orderLineStorage.getOrderLinesContainItemSetByDistrict(w_id, d_id, given_i_id_l);
                if (contain_items_o_id_l.size() == 0){
//                    System.out.println("contain_items_o_id_l == null");
                    continue;
                }
                else{
//                    System.out.println("contain_items_o_id_l != null");
                    for(int i: contain_items_o_id_l){
//                        System.out.println("contain_items_o_id_l: "+i);
                    }
                    List<Integer> c_id_l = orderStorage.getAllCIdByOIds(w_id, d_id, contain_items_o_id_l);
                    for (int c_id : c_id_l){
//                        System.out.print("\nc_id = "+c_id);
                        List<Integer> o_id_l = orderStorage.getAllOrderIdByCustomer(w_id, d_id, c_id);
                        for (int o_id : o_id_l){
//                            System.out.print("o_id = "+o_id + "   ");
                            List<OrderLine> ols = orderLineStorage.getOrderLinesContainItemSetByOneOrder(w_id, d_id, o_id, given_i_id_l);
                            if (ols != null){
                                int count = 0;
                                for (OrderLine ol: ols){
//                                    System.out.print("ol_count "+ count + "\t");
                                    if(given_i_id_l.contains(ol.getOl_i_id())){
                                        count++;
//                                        System.out.print("-----------ol_count+1 = "+ count + "------------\t");
                                    }
                                    if (count == 2){
                                        String c_identifier = w_id + " - " + d_id + " - " +  c_id;

//                                        System.out.println("c_identifier = "+c_identifier+"\n\n\n\n\n\n\n\n");

                                        c_identifiers.add(c_identifier);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
//outputs:
        for (String c_identifier: c_identifiers){
            System.out.println(c_identifier);
        }

    }

    @Test
    public void getOrders(){
//        List<Order> orders = orderStorage.getOrders(1, 1, Arrays.asList(1, 2, 3));
//        orders.forEach(o -> {
//            System.out.println(o);
//        });
    }

    @Test
    public void getCustomers(){
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        Map<Integer, List<Customer>> customerMaps = customerStorage.getCustomers(1, 1, set).stream().collect(Collectors.groupingBy(Customer::getC_id));

        System.out.println("aaa");
    }

}