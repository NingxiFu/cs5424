/**
 * @(#)StockStorageImpl.java, Oct 27, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage.db;

import com.nus.cs5424.data.Stock;
import com.nus.cs5424.storage.BaseStorage;
import com.nus.cs5424.storage.StockStorage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guochenghui
 */
@Repository
public class StockStorageImpl extends BaseStorage implements StockStorage {

    private static final String TABLE = "\"Stock\"";

    @Override
    public Stock query(int s_w_id, int s_i_id) {
        String sql = String.format("SELECT * FROM " + TABLE + " WHERE \"S_W_ID\" = %d AND \"S_I_ID\" = %d", s_w_id, s_i_id);
        Stock stock = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Stock>(Stock.class));
        return stock;
    }

    @Override
    public Integer getNumOfItemBelowThresholdByWarehouse(int s_w_id, List<Integer> item_ids, int threshold) {
        String sql = "SELECT COUNT(\"S_I_ID\") FROM " + TABLE + " WHERE \"S_W_ID\" = " + s_w_id
                + " AND " +  "\"S_I_ID\" IN " + item_ids.toString().replace('[','(').replace(']', ')')
                + " AND " +  "\"S_QUANTITY\" < " + threshold;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}