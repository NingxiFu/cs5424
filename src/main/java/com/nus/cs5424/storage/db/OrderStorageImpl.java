/**
 * @(#)OrderStorageImpl.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage.db;

import com.nus.cs5424.data.Order;
import com.nus.cs5424.storage.BaseStorage;
import com.nus.cs5424.storage.OrderStorage;
import org.springframework.stereotype.Repository;

/**
 * @author guochenghui
 */
@Repository
public class OrderStorageImpl extends BaseStorage implements OrderStorage {

    private static final String TABLE = "\"Order\"";

    @Override
    public Order add(Order order) {
        String sql = "INSERT INTO " + TABLE + " (\"O_ID\", \"O_D_ID\", \"O_W_ID\", \"O_C_ID\", \"O_ENTRY_D\", \"O_OL_CNT\", \"O_ALL_LOCAL\") VALUES (" +
                + order.getO_id() + ", "
                + order.getO_d_id() + ", "
                + order.getO_w_id() + ", "
                + order.getO_c_id() + ", "
                + "\'" + order.getO_entry_d() + "\'" + ", "
                + order.getO_ol_cnt() + ", "
                + order.getO_all_local() +" )";

        jdbcTemplate.update(sql);
        return order;
    }
}