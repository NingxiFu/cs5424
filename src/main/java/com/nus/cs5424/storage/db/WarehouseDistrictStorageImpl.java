/**
 * @(#)DistrictStorageImpl.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage.db;

import com.nus.cs5424.data.WarehouseDistrict;
import com.nus.cs5424.storage.BaseStorage;
import com.nus.cs5424.storage.WarehouseDistrictStorage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author guochenghui
 */
@Repository
public class WarehouseDistrictStorageImpl extends BaseStorage implements WarehouseDistrictStorage {

    private static final String TABLE = "\"WarehouseDistrict\"";

    @Override
    public Integer getNext_O_IDByPrimaryKey(int w_id, int d_id) {
        String sql = "SELECT \"D_NEXT_O_ID\" FROM " + TABLE + " WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public WarehouseDistrict getWarehouseDistrictByIdentifier(int w_id, int d_id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<WarehouseDistrict>(WarehouseDistrict.class));
    }

    @Override
    public WarehouseDistrict getWarehouseDistrictOnlyReadByIdentifier(int w_id, int d_id) {
        String sql = "SELECT * FROM " + "\"WarehouseDistrict_Read\"" + " WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<WarehouseDistrict>(WarehouseDistrict.class));
    }

    @Override
    public boolean updateNext_O_ID(int w_id, int d_id, int D_NEXT_O_ID) {
        String sql = "UPDATE " + TABLE + " SET \"D_NEXT_O_ID\" = " + D_NEXT_O_ID + "WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
        return jdbcTemplate.update(sql) > 0;
    }

    @Override
    public WarehouseDistrict updateD_YTDByPayment(int w_id, int d_id, BigDecimal payment) {
        String sql = "UPDATE " + TABLE + " SET \"D_YTD\" = \"D_YTD\" + " + payment + " WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id + " RETURNING *";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<WarehouseDistrict>(WarehouseDistrict.class));
    }

    @Override
    public boolean updateW_YTDByPayment(int w_id, BigDecimal payment){//merge up
        String sql = "UPDATE " + TABLE + " SET \"W_YTD\" = \"W_YTD\" + " + payment + " WHERE \"D_W_ID\" = " + w_id;
        return jdbcTemplate.update(sql) > 0;
    }

//UPDATE 表名 SET 字段1= CASE WHEN 条件1 THEN 1 ELSE 0 END, 字段2= CASE WHEN 条件2 THEN 0 ELSE 1 END
//    @Override
//    public boolean updateW_YTDByPayment(int w_id, BigDecimal payment){//merge up
//        String sql = "UPDATE " + TABLE + " SET \"W_YTD\" = \"W_YTD\" + " + payment + " WHERE \"W_ID\" = " + w_id;
//        return jdbcTemplate.update(sql) > 0;
//    }
}