package com.nus.cs5424.txs;

import com.nus.cs5424.data.Customer;
import com.nus.cs5424.data.Order;
import com.nus.cs5424.data.OrderLine;
import com.nus.cs5424.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockLevel implements transaction{
    @Autowired
    CustomerStorage customerStorage;

    @Autowired
    ItemStorage itemStorage;

    @Autowired
    WarehouseDistrictStorage warehouseDistrictStorage;

    @Autowired
    OrderStorage orderStorage;

    @Autowired
    StockStorage stockStorage;

    @Autowired
    OrderLineStorage orderLineStorage;

    @Override
    public void process(String[] args) {
//2.5 Stock-level Transaction
        int w_id = Integer.parseInt(args[1]);
        int d_id = Integer.parseInt(args[2]);
        int threshold = Integer.parseInt(args[3]);
        int num_last_orders = Integer.parseInt(args[4]);
//transaction:
        int next_o_id = warehouseDistrictStorage.getNext_O_IDByPrimaryKey(w_id, d_id);
        List<Integer> item_ids = orderLineStorage.getItemIdsByLastOrders(w_id, d_id, next_o_id, num_last_orders);
        int num_of_i = stockStorage.getNumOfItemBelowThresholdByWarehouse(w_id, item_ids, threshold);
//outputs:
        System.out.println("Total number of items where stock below threshold: " + num_of_i);
    }
}
