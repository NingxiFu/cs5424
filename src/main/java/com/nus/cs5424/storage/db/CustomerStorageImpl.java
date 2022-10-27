/**
 * @(#)CustomerStorageImpl.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage.db;

import com.nus.cs5424.data.Customer;
import com.nus.cs5424.storage.BaseStorage;
import com.nus.cs5424.storage.CustomerStorage;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author guochenghui
 */
@Repository
public class CustomerStorageImpl extends BaseStorage implements CustomerStorage {

    private static final String TABLE = "\"Customer\"";

    @Override
    public Customer getCustomerByIdentifier(int c_w_id, int c_d_id, int c_id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE \"C_W_ID\" = " + c_w_id + " AND " +  "\"C_D_ID\" = " + c_d_id + " AND " +  "\"C_ID\" = " + c_id;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
    }

    @Override
    public boolean updateByPayment(int c_w_id, int c_d_id, int c_id, int payment) {
        String sql = "UPDATE " + TABLE + " SET \"C_BALANCE\" = \"C_BALANCE\" - " + payment +
                ", \"C_YTD_PAYMENT\" = \"C_YTD_PAYMENT\" + " + payment + ", \"C_PAYMENT_CNT\" = \"C_PAYMENT_CNT\" + 1 " +
                " WHERE \"C_W_ID\" = " + c_w_id + " AND " +  "\"C_D_ID\" = " + c_d_id + " AND " +  "\"C_ID\" = " + c_id;
        return jdbcTemplate.update(sql) > 0;
    }
//
//    @Override
//    public Integer getNext_O_IDByPrimaryKey(int w_id, int d_id) {
//        String sql = "SELECT \"D_NEXT_O_ID\" FROM " + TABLE + " WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
//        return jdbcTemplate.queryForObject(sql, Integer.class);
//    }
//
//    @Override
//    public boolean updateNext_O_ID(int w_id, int d_id) {
//        String sql = "UPDATE " + TABLE + " SET \"D_NEXT_O_ID\" = \"D_NEXT_O_ID\" + 1 WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
//        return jdbcTemplate.update(sql) > 0;
//    }
//
//    @Override
//    public boolean updateD_YTDByPayment(int w_id, int d_id, int payment) {
//        String sql = "UPDATE " + TABLE + " SET \"D_YTD\" = \"D_YTD\" + " + payment + " WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
//        return jdbcTemplate.update(sql) > 0;
//    }
}