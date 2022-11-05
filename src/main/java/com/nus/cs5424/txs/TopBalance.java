package com.nus.cs5424.txs;

import com.nus.cs5424.data.Customer;
import com.nus.cs5424.data.WarehouseDistrict;
import com.nus.cs5424.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopBalance implements transaction{
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

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void process(String[] args) {
//2.7 Top-Balance Transaction
//transaction:
        List<Customer> cs = customerStorage.getCustomersByTopBalance();
//outputs:
        for (Customer c : cs){
            WarehouseDistrict wd = warehouseDistrictStorage.getWarehouseDistrictByIdentifier(c.getC_w_id(), c.getC_d_id());
            System.out.println("Customer's Name: " + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last() + ",\tBalance: " + c.getC_balance()
            + "\nWarehouse's Name: " + wd.getW_name()
            + "\nDistrict's Name: " + wd.getD_name());
        }
    }
}
