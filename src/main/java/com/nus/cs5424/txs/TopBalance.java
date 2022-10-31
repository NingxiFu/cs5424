package com.nus.cs5424.txs;

import com.nus.cs5424.data.Customer;
import com.nus.cs5424.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopBalance implements transaction{
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
//transaction:
        List<Customer> cs = customerStorage.getCustomersByTopBalance();
//outputs:
        for (Customer c : cs){
            System.out.println("Customer's Name: " + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last() + ",\tBalance: " + c.getC_balance());
            System.out.println("Warehouse's Name: " + warehouseStorage.getWarehouseByIdentifier(c.getC_w_id()).getW_name());
            System.out.println("District's Name: " + districtStorage.getDistrictByIdentifier(c.getC_w_id(), c.getC_d_id()).getD_name() + "\n");
        }
    }
}
