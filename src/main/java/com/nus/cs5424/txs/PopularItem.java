package com.nus.cs5424.txs;

import com.nus.cs5424.data.Customer;
import com.nus.cs5424.data.Item;
import com.nus.cs5424.data.Order;
import com.nus.cs5424.data.OrderLine;
import com.nus.cs5424.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PopularItem implements transaction{
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

    @Override
    public void process(String[] args) {
        int w_id = Integer.parseInt(args[1]);
        int d_id = Integer.parseInt(args[2]);
        int num_last_orders = Integer.parseInt(args[3]);
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
                System.out.println("Item name: " + items.get(i_id) + " \tQuantity ordered: " + ol.getOl_quantity());
            }
        }
        System.out.println("\nThe percentage of examined orders that contain each popular item:");
        for (Object i_id : items.keySet()){
            int count = orderLineStorage.getCountByLastOrdersContainsTheItem(w_id, d_id, Integer.parseInt(i_id.toString()), next_o_id, num_last_orders);
            System.out.println("Item name: " + items.get(i_id)  + " \tPercentage: " + String.format("%.2f", (float)count*100/num_last_orders) + "%");
        }
    }
}
