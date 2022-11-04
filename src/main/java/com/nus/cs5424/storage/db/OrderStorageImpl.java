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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    @Override
    public Order getLastOrderByCustomer(int o_w_id, int o_d_id, int o_c_id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE \"O_W_ID\" = " + o_w_id + " AND " + "\"O_D_ID\" = " + o_d_id + " AND " + "\"O_C_ID\" = " + o_c_id
                + " ORDER BY \"O_ENTRY_D\" DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class));
    }

    @Override
    public List<Integer> getAllOrderIdByCustomer(int o_w_id, int o_d_id, int o_c_id) {
        String sql = "SELECT \"O_ID\" FROM " + TABLE + " WHERE \"O_W_ID\" = " + o_w_id + " AND " + "\"O_D_ID\" = " + o_d_id + " AND " + "\"O_C_ID\" = " + o_c_id;
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    @Override
    public List<Integer> getAllCIdByOIds(int o_w_id, int o_d_id, List<Integer> o_ids) {
        String sql = "SELECT DISTINCT \"O_C_ID\" FROM " + TABLE + " WHERE \"O_W_ID\" = " + o_w_id + " AND " + "\"O_D_ID\" = " + o_d_id + " AND "
                + "\"O_ID\" IN " + o_ids.toString().replace('[','(').replace(']', ')');
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    @Override
    public Order getOldestOrderByDistrict(int o_w_id, int o_d_id) {
        //Oldest order yet-to-be-delivered order in a district, if none, return -1
        String sql = "SELECT * FROM " + TABLE + " WHERE \"O_W_ID\" = " + o_w_id + " AND " + "\"O_D_ID\" = " + o_d_id + " AND \"O_CARRIER_ID\" IS NULL ORDER BY \"O_ID\" ASC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class));
    }

    @Override
    public boolean updateCarrierIdByOldestOrder(int o_w_id, int o_d_id, int o_id, int o_carrier_id) {
        String sql = "UPDATE " + TABLE + " SET \"O_CARRIER_ID\" = " + o_carrier_id +
                " WHERE \"O_W_ID\" = " + o_w_id + " AND " +  "\"O_D_ID\" = " + o_d_id + " AND " +  "\"O_ID\" = " + o_id;
        return jdbcTemplate.update(sql) > 0;
    }

    @Override
    public Order getLastOrderByIdentifier(int o_w_id, int o_d_id, int o_id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE \"O_W_ID\" = " + o_w_id + " AND " + "\"O_D_ID\" = " + o_d_id + " AND " + "\"O_ID\" = " + o_id;
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class));
    }

    @Override
    public List<Order> getOrders(int o_w_id, int o_d_id, Set<Integer> o_ids) {
        String sql =  "SELECT * FROM " + TABLE + " WHERE \"O_W_ID\" = " + o_w_id + " AND " + "\"O_D_ID\" = " + o_d_id + " AND " + "\"O_ID\" IN " + o_ids.toString().replace('[','(').replace(']', ')');
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Order>(Order.class));
    }


}