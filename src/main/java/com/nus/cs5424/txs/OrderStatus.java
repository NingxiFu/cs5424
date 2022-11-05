package com.nus.cs5424.txs;

import com.nus.cs5424.data.Customer;
import com.nus.cs5424.data.Order;
import com.nus.cs5424.data.OrderLine;
import com.nus.cs5424.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderStatus implements transaction{
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

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void process(String[] args) {
//2.4 Order-Status Transaction
        int c_w_id = Integer.parseInt(args[1]);
        int c_d_id = Integer.parseInt(args[2]);
        int c_id = Integer.parseInt(args[3]);
//transaction:
        Customer c = customerStorage.getCustomerByIdentifier(c_w_id, c_d_id, c_id);
        Order o = orderStorage.getLastOrderByCustomer(c_w_id, c_d_id, c_id);
        List<OrderLine> ols = orderLineStorage.getOrderLinesByOneOrder(o.getO_w_id(), o.getO_d_id(), o.getO_id());
//outputs:
        System.out.println("Customer's Name: " + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last() +
                ",\tBalance: " + c.getC_balance()
                + "\nCustomer's Last Order ---- Order number: " + o.getO_id() + ",\tEntry date and time: " + o.getO_entry_d() + ",\tCarrier identifier: " + o.getO_carrier_id());
        for (OrderLine ol : ols){
            System.out.println("Item number: " + ol.getOl_i_id() + ",\tSupplying warehouse number: " + ol.getOl_supply_w_id() + ",\tQuantity ordered: "
                    + ol.getOl_quantity() + ",\tTotal price for ordered item: " + ol.getOl_amount() + ",\tData and time of delivery: " + ol.getOl_delivery_d());
        }
    }
}
