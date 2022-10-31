/**
 * @(#)OrderStorage.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

import com.nus.cs5424.data.Order;

import java.sql.SQLException;

/**
 * @author guochenghui
 */
public interface OrderStorage {

    Order add(Order order);

    Order getLastOrderByCustomer(int o_w_id, int o_d_id, int o_c_id);

    Order getOldestOrderByDistrict(int w_id, int d_id);

    boolean updateCarrierIdByOldestOrder(int o_w_id, int o_d_id, int o_id, int o_carrier_id);

    Order getLastOrderByIdentifier(int o_w_id, int o_d_id, int o_id);
}