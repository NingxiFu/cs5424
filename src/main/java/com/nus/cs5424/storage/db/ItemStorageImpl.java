/**
 * @(#)ItemStorageImpl.java, Oct 29, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage.db;

import com.nus.cs5424.data.Item;
import com.nus.cs5424.storage.BaseStorage;
import com.nus.cs5424.storage.ItemStorage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author guochenghui
 */
@Repository
public class ItemStorageImpl extends BaseStorage implements ItemStorage {

    private static final String TABLE = "\"Item\"";

    @Override
    public Item query(int i_id) {
        String sql = String.format("SELECT * FROM " + TABLE + " Where \"I_ID\" =  %d", i_id);
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Item.class));
    }
}