package com.nus.cs5424.txs;

import com.nus.cs5424.data.Customer;
import com.nus.cs5424.data.District;
import com.nus.cs5424.data.Order;
import com.nus.cs5424.data.Warehouse;
import com.nus.cs5424.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Delivery implements transaction{
    @Autowired
    CustomerStorage customerStorage;

    @Autowired
    ItemStorage itemStorage;

    @Autowired
    OrderStorage orderStorage;

    @Autowired
    StockStorage stockStorage;

    @Autowired
    OrderLineStorage orderLineStorage;

    @Override
    public void process(String[] args) {
//2.3 Delivery Transaction
        int w_id = Integer.parseInt(args[1]);
        int carrier_id = Integer.parseInt(args[2]);
//transaction:
        for (int d_id = 1; d_id <= 10; d_id++) { //when debugging, can change to d_id <= 1.
            Order o = orderStorage.getOldestOrderByDistrict(w_id, d_id);
            int o_id = o.getO_id();
            int c_id = o.getO_c_id();
//            System.out.println(o_id + " " + c_id);

            orderStorage.updateCarrierIdByOldestOrder(w_id, d_id, o_id, carrier_id);
            orderLineStorage.updateDelivery_DByOneOrder(w_id, d_id, o_id);
            BigDecimal ol_amount_sum = orderLineStorage.getSumOfAmountByOneOrder(w_id, d_id, o_id);
            customerStorage.updateByDelivery(w_id, d_id, c_id, ol_amount_sum);
        }
    }
}
