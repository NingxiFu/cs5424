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
        int c_w_id = Integer.parseInt(args[1]);
        int c_d_id = Integer.parseInt(args[2]);
        int c_id = Integer.parseInt(args[3]);
        BigDecimal payment = BigDecimal.valueOf(Float.parseFloat(args[4]));
        warehouseStorage.updateW_YTDByPayment(c_w_id, payment);
        districtStorage.updateD_YTDByPayment(c_w_id, c_d_id, payment);
        customerStorage.updateByPayment(c_w_id, c_d_id, c_id, payment);
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
}
