/**
 * @(#)OrderLineStorageImpl.java, Oct 27, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage.db;

import com.nus.cs5424.data.OrderLine;
import com.nus.cs5424.storage.BaseStorage;
import com.nus.cs5424.storage.OrderLineStorage;
import org.springframework.stereotype.Repository;

/**
 * @author guochenghui
 */
@Repository
public class OrderLineStorageImpl extends BaseStorage implements OrderLineStorage {

    private static final String TABLE = "\"OrderLine\"";

    @Override
    public OrderLine add(OrderLine orderLine) {
        String sql = String.format("INSERT INTO " + TABLE
                + " (\"OL_O_ID\", \"OL_D_ID\", \"OL_W_ID\", \"OL_NUMBER\", \"OL_I_ID\", \"OL_SUPPLY_W_ID\", \"OL_QUANTITY\", \"OL_AMOUNT\", \"OL_DIST_INFO\") "
                + "VALUES (%d, %d, %d, %d, %d, %d, %s, %s, '%s')",
                orderLine.getOl_o_id(), orderLine.getOl_d_id(), orderLine.getOl_w_id(), orderLine.getOl_number(),
                orderLine.getOl_i_id(),orderLine.getOl_supply_w_id(), orderLine.getOl_quantity(),
                orderLine.getOl_amount(), orderLine.getOl_dist_info());

        jdbcTemplate.update(sql);
        return orderLine;
    }
}