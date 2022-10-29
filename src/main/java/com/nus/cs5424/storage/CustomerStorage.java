/**
 * @(#)CustomerStorage.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

import com.nus.cs5424.data.Customer;

/**
 * @author guochenghui
 */
public interface CustomerStorage {

    Customer getCustomerByIdentifier(int c_w_id, int c_d_id, int c_id);

    boolean updateByPayment(int c_w_id, int c_d_id, int c_id, int payment);

    boolean updateByDelivery(int c_w_id, int c_d_id, int c_id, int ol_amount_sum);
}