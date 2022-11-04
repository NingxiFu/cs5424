package com.nus.cs5424.txs;
import com.nus.cs5424.data.*;
import com.nus.cs5424.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Payment implements transaction{
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
//2.2 Payment Transaction
        int c_w_id = Integer.parseInt(args[1]);
        int c_d_id = Integer.parseInt(args[2]);
        int c_id = Integer.parseInt(args[3]);
        BigDecimal payment = BigDecimal.valueOf(Float.parseFloat(args[4]));
        warehouseDistrictStorage.updateD_YTDByPayment(c_w_id, c_d_id, payment);
        warehouseDistrictStorage.updateW_YTDByPayment(c_w_id, payment);
        customerStorage.updateByPayment(c_w_id, c_d_id, c_id, payment);
        Customer c = customerStorage.getCustomerByIdentifier(c_w_id, c_d_id, c_id);
        WarehouseDistrict wd = warehouseDistrictStorage.getWarehouseDistrictByIdentifier(c_w_id, c_d_id);
        System.out.println("Customer's Identifier: " + c.getC_w_id() + ", " + c.getC_d_id() + + c.getC_id() + ",\tName: "
                + c.getC_first() + ", " + c.getC_middle() + ", " + c.getC_last() + ",\tAddress: "
                + c.getC_street_1() + ", " + c.getC_street_2() + ", " + c.getC_city() + ", " + c.getC_state() + ", " + c.getC_zip()
                + ",\tPhone: " + c.getC_phone() + ",\tSince: " + c.getC_since() + ",\tCredit: " + c.getC_credit()
                + ",\tCredit limit: " + c.getC_credit_lim() + ",\tDiscount: " + c.getC_discount() + ",\tBalance: " + c.getC_balance()
                + "\nWarehouse address: " + wd.getW_street_1() + ", " + wd.getW_street_2() + ", " + wd.getW_city() + ", " + wd.getW_state() + ", " + wd.getW_zip()
                + "District address: " + wd.getD_street_1() + ", " + wd.getD_street_2() + ", " + wd.getD_city() + ", " + wd.getD_state() + ", " + wd.getD_zip());
    }
}
