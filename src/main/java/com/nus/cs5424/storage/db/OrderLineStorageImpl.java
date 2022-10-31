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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<OrderLine> getOrderLinesByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE \"OL_W_ID\" = " + ol_w_id + " AND " + "\"OL_D_ID\" = " + ol_d_id + " AND " + "\"OL_O_ID\" = " + ol_o_id
                + " ORDER BY \"OL_NUMBER\" ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<OrderLine>(OrderLine.class));
    }

    @Override
    public List<OrderLine> getOrderLinesContainItemSetByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id, Set<Integer> given_i_id_l) {
        String sql = "SELECT * FROM " + TABLE + " WHERE \"OL_W_ID\" = " + ol_w_id + " AND " + "\"OL_D_ID\" = " + ol_d_id + " AND " + "\"OL_O_ID\" = " + ol_o_id
                + " AND \"OL_I_ID\" IN " + given_i_id_l.toString().replace('[','(').replace(']', ')');
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<OrderLine>(OrderLine.class));
    }

    @Override
    public List<Integer> getOrderLinesContainItemSetByDistrict(int ol_w_id, int ol_d_id, Set<Integer> given_i_id_l) {
        String sql = "SELECT \"OL_O_ID\" FROM " + TABLE + " WHERE \"OL_W_ID\" = " + ol_w_id + " AND " + "\"OL_D_ID\" = " + ol_d_id
                + " AND \"OL_I_ID\" IN " + given_i_id_l.toString().replace('[','(').replace(']', ')')
                + " GROUP BY \"OL_O_ID\" HAVING COUNT(\"OL_I_ID\") >= 2";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    @Override
    public boolean updateDelivery_DByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id) {
        String sql = "UPDATE " + TABLE + " SET \"OL_DELIVERY_D\" = current_timestamp" +
                " WHERE \"OL_W_ID\" = " + ol_w_id + " AND " +  "\"OL_D_ID\" = " + ol_d_id + " AND " +  "\"OL_O_ID\" = " + ol_o_id;
        return jdbcTemplate.update(sql) > 0;
    }

    @Override
    public BigDecimal getSumOfAmountByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id) {
        String sql = "SELECT SUM(\"OL_AMOUNT\") FROM " + TABLE + " WHERE \"OL_W_ID\" = " + ol_w_id + " AND " + "\"OL_D_ID\" = " + ol_d_id + " AND " + "\"OL_O_ID\" = " + ol_o_id;
        return jdbcTemplate.queryForObject(sql, BigDecimal.class);
    }

    @Override
    public List<Integer> getItemIdsByLastOrders(int ol_w_id, int ol_d_id, int next_o_id, int num_last_orders) {
        int left = next_o_id - num_last_orders;
        int right = next_o_id - 1;
        String sql = "SELECT \"OL_I_ID\" FROM " + TABLE + " WHERE \"OL_W_ID\" = " + ol_w_id + " AND " + "\"OL_D_ID\" = " + ol_d_id + " AND "
                + "\"OL_O_ID\" BETWEEN " + left + " AND " + right;
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    @Override
    public List<OrderLine> getOrderlinesByPopularItemsInOneOrder(int ol_w_id, int ol_d_id, int ol_o_id) {
        String sql = "WITH \"tops\" AS (SELECT * FROM \"OrderLine\" WHERE \"OL_W_ID\" = " + ol_w_id + " AND \"OL_D_ID\" = " + ol_d_id + " AND \"OL_O_ID\" = " + ol_o_id
                + ") SELECT * FROM \"tops\" WHERE \"OL_QUANTITY\" = (SELECT MAX(\"OL_QUANTITY\") FROM  \"tops\")";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<OrderLine>(OrderLine.class));
    }

    @Override
    public Integer getCountByLastOrdersContainsTheItem(int ol_w_id, int ol_d_id, int ol_i_id, int next_o_id, int num_last_orders) {
        int left = next_o_id - num_last_orders;
        int right = next_o_id - 1;
        String sql = "SELECT COUNT(*) FROM " + TABLE + " WHERE \"OL_W_ID\" = " + ol_w_id + " AND " + "\"OL_D_ID\" = " + ol_d_id + " AND "
                + "\"OL_O_ID\" BETWEEN " + left + " AND " + right + " AND \"OL_I_ID\" = " + ol_i_id;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }



}