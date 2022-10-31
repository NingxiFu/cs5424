/**
 * @(#)tx2.java, Oct 27, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.txs;

import com.nus.cs5424.data.Customer;
import com.nus.cs5424.data.District;
import com.nus.cs5424.data.Item;
import com.nus.cs5424.data.Order;
import com.nus.cs5424.data.OrderLine;
import com.nus.cs5424.data.Stock;
import com.nus.cs5424.data.Warehouse;
import com.nus.cs5424.storage.CustomerStorage;
import com.nus.cs5424.storage.DistrictStorage;
import com.nus.cs5424.storage.ItemStorage;
import com.nus.cs5424.storage.OrderLineStorage;
import com.nus.cs5424.storage.OrderStorage;
import com.nus.cs5424.storage.StockStorage;
import com.nus.cs5424.storage.WarehouseStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

import java.math.BigDecimal;

/**
 * @author guochenghui
 */
@Service
public class NewOrder implements transaction{

    @Autowired
    CustomerStorage customerStorage;

    @Autowired
    ItemStorage itemStorage;

    @Autowired
    WarehouseStorage warehouseStorage;

    @Autowired
    DistrictStorage districtStorage;

    @Autowired
    OrderStorage orderStorage;

    @Autowired
    StockStorage stockStorage;

    @Autowired
    OrderLineStorage orderLineStorage;

    @Transactional
    @Override
    public void process(String[] args) {
        // TODO 完成tx的编写和输出
        // 1. get D_NEXT_O_ID
        // 2. update D_NEXT_O_ID
        // 3. insert NEW Order
        // 4. iterate Items
        int c_id = Integer.parseInt(args[1]);
        int w_id = Integer.parseInt(args[2]);
        int d_id = Integer.parseInt(args[3]);
        BigDecimal o_ol_cnt = new BigDecimal(args[4]);
        List<OrderLine> orderLines = parseOrderLines(args[5]);

        Customer customer = customerStorage.getCustomerByIdentifier(w_id, d_id, c_id);
        Warehouse warehouse = warehouseStorage.getWarehouseByIdentifier(w_id);
        District district = districtStorage.getDistrictByIdentifier(w_id, d_id);

        //get
        int d_next_o_id = district.getD_next_o_id();

        //insert
        int o_id = d_next_o_id;
        BigDecimal o_all_local = new BigDecimal(isAllLocal(w_id, orderLines) ? 1 : 0);
        Order order = new Order(w_id, d_id, o_id, c_id, o_ol_cnt, o_all_local, new Timestamp(System.currentTimeMillis()));
        orderStorage.add(order);

        //update
        boolean u = districtStorage.updateNext_O_ID(w_id, d_id, o_id + 1);
        if(!u) System.out.println("更新失败");


        // output
        String c_last = customer.getC_last();
        String c_credit = customer.getC_credit();
        BigDecimal c_discount = customer.getC_discount();
        System.out.printf(
                "(W_ID, D_ID, C_ID): (%d, %d, %d), C_LAST: %s, C_CREDIT: %s, C_DISCOUNT: %f\n",
                w_id, d_id, c_id, c_last, c_credit, c_discount.floatValue()
        );
        BigDecimal w_tax = warehouse.getW_tax();
        BigDecimal d_tax = district.getD_tax();
        System.out.printf("W_TAX: %f, D_TAX: %f\n",
                w_tax.floatValue(), d_tax.floatValue());


        double totalAmount = insertOrderLines(w_id, d_id, o_id, orderLines);

        BigDecimal c_tax = customer.getC_discount();
        totalAmount = totalAmount
                * (1 + w_tax.add(d_tax).doubleValue())
                * (1 - c_tax.doubleValue());

        //output
        System.out.printf("NUM_ITEMS: %d, TOTAL_AMOUNT: %f\n",
                o_ol_cnt.toBigInteger(), totalAmount);

    }


    private List<OrderLine> parseOrderLines(String orderLinesString) {
        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        String[] orderLinesStrArr = orderLinesString.split("\n");
        for (int i = 0; i < orderLinesStrArr.length; i++) {
            String[] args = orderLinesStrArr[i].split(",");
            // ol_number
            // ol_i_id
            // ol_supply_w_id
            // ol_quantity
            OrderLine orderLine = new OrderLine(
                    i + 1,
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    new BigDecimal(args[2]));
            orderLines.add(orderLine);
        }
        return orderLines;
    }

    private boolean isAllLocal(int w_id, List<OrderLine> orderLines) {
        for (OrderLine orderLine : orderLines) {
            int ol_supply_w_id = orderLine.getOl_supply_w_id();
            if (w_id != ol_supply_w_id) {
                return false;
            }
        }
        return true;
    }

    private double insertOrderLines(int w_id, int d_id, int o_id, List<OrderLine> orderLines){
        double totalAmount = 0;
        String ol_dist_info = String.format("S_DIST_%02d", d_id);

        for (OrderLine orderLine : orderLines) {
            int ol_supply_w_id = orderLine.getOl_supply_w_id();
            int i_id = orderLine.getOl_i_id();
            BigDecimal ol_quantity = orderLine.getOl_quantity();

            // stock update
            Stock stock = stockStorage.query(w_id, i_id);

            BigDecimal s_quantity = stock.getS_quantity();
            BigDecimal s_ytd = stock.getS_ytd();
            int s_order_cnt = stock.getS_order_cnt();
            int s_remote_cnt = stock.getS_remote_cnt();

            s_quantity = s_quantity.add(ol_quantity.negate());
            if (s_quantity.doubleValue() < 10) {
                s_quantity = s_quantity.add(new BigDecimal(100));
            }
            s_ytd = s_ytd.add(ol_quantity);
            s_order_cnt++;
            if (w_id != ol_supply_w_id) { s_remote_cnt++; };

            boolean update = stockStorage.update(ol_supply_w_id, i_id, s_quantity, s_ytd, s_order_cnt, s_remote_cnt);
            if(!update) System.out.println("更新失败");


            // orderline insert
            Item item = itemStorage.query(i_id);
            BigDecimal i_price = item.getI_price();
            String i_name = item.getI_name();

            int ol_n = orderLine.getOl_number();
            Timestamp ol_delivery_d = null;
            BigDecimal ol_amount = ol_quantity.multiply(i_price);

            orderLine = new OrderLine(w_id, d_id, o_id, ol_n, i_id, ol_delivery_d, ol_amount,
                    ol_supply_w_id, ol_quantity, ol_dist_info);

            orderLineStorage.add(orderLine);

            totalAmount += ol_amount.doubleValue();

            System.out.printf(
                    "  ITEM_NUMBER: %d, I_NAME: %s, SUPPLIER_WAREHOUSE: %d, QUANTITY: %d, OL_AMOUNT: %f, S_QUANTITY: %d\n",
                    i_id, i_name, ol_supply_w_id, ol_quantity.toBigInteger(), ol_amount, s_quantity.toBigInteger()
            );

        }
        return totalAmount;
    }
}