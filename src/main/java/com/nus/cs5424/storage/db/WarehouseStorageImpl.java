/**
 * @(#)WarehouseStorageImpl.java, Oct 27, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage.db;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.nus.cs5424.data.Warehouse;
import com.nus.cs5424.storage.BaseStorage;
import com.nus.cs5424.storage.WarehouseStorage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author guochenghui
 */
public class WarehouseStorageImpl extends BaseStorage implements WarehouseStorage {

    private static final String TABLE = "\"Warehouse\"";

    @Override
    public Warehouse query(int w_id) {
        String sql = "SELECT * FROM " + TABLE + " Where \"W_ID\" = " + w_id;
        Warehouse warehouse = jdbcTemplate.queryForObject(sql, new RowMapper<Warehouse>() {
            @Override
            public Warehouse mapRow(ResultSet rs, int rowNum) throws SQLException {
                Warehouse warehouse = new Warehouse();
                warehouse.setId(rs.getInt("W_ID"));
                warehouse.setW_name(rs.getString("W_NAME"));
                return warehouse;
            }
        });

        return warehouse;
    }
}