/**
 * @(#)CustomerStorage.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

import com.nus.cs5424.data.Customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author guochenghui
 */
public interface CustomerStorage {

    Customer getCustomerByIdentifier(int c_w_id, int c_d_id, int c_id);

    List<Customer> getCustomers(int c_w_id, int c_d_id, Set<Integer> c_id);

    List<Integer> getCustomerByDistrict(int c_w_id, int c_d_id);

    boolean updateByPayment(int c_w_id, int c_d_id, int c_id, BigDecimal payment);

    boolean updateByDelivery(int c_w_id, int c_d_id, int c_id, BigDecimal ol_amount_sum);

    List<Customer> getCustomersByTopBalance();
}