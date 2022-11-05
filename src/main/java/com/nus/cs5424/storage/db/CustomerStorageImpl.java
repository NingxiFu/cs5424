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

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
    public List<Customer> getCustomers(int c_w_id, int c_d_id, Set<Integer> c_id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE \"C_W_ID\" = " + c_w_id + " AND " +  "\"C_D_ID\" = " + c_d_id + " AND " +  "\"C_ID\" IN " + c_id.toString().replace('[','(').replace(']', ')');
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    @Override
    public List<Integer> getCustomerByDistrict(int c_w_id, int c_d_id) {
        String sql = "SELECT \"C_ID\" FROM " + TABLE + " WHERE \"C_W_ID\" = " + c_w_id + " AND " +  "\"C_D_ID\" = " + c_d_id;
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    @Override
    public Customer updateByPayment(int c_w_id, int c_d_id, int c_id, BigDecimal payment) {
        String sql = "UPDATE " + TABLE + " SET \"C_BALANCE\" = \"C_BALANCE\" - " + payment +
                ", \"C_YTD_PAYMENT\" = \"C_YTD_PAYMENT\" + " + payment + ", \"C_PAYMENT_CNT\" = \"C_PAYMENT_CNT\" + 1 " +
                " WHERE \"C_W_ID\" = " + c_w_id + " AND " +  "\"C_D_ID\" = " + c_d_id + " AND " +  "\"C_ID\" = " + c_id + " RETURNING *";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
    }

    @Override
    public boolean updateByDelivery(int c_w_id, int c_d_id, int c_id, BigDecimal ol_amount_sum) {
        String sql = "UPDATE " + TABLE + " SET \"C_BALANCE\" = \"C_BALANCE\" + " + ol_amount_sum +
                ", \"C_DELIVERY_CNT\" = \"C_DELIVERY_CNT\" + 1 " +
                " WHERE \"C_W_ID\" = " + c_w_id + " AND " +  "\"C_D_ID\" = " + c_d_id + " AND " +  "\"C_ID\" = " + c_id;
        return jdbcTemplate.update(sql) > 0;
    }

    @Override
    public boolean updateByDeliveryContainsOlSelect(int c_w_id, int c_d_id, int c_id, int o_id) {
        String sql = "UPDATE " + TABLE + " SET \"C_BALANCE\" = \"C_BALANCE\" + "
                + "(SELECT SUM(\"OL_AMOUNT\") FROM \"OrderLine\" WHERE \"OL_W_ID\" = " + c_w_id + " AND " + "\"OL_D_ID\" = " + c_d_id + " AND " + "\"OL_O_ID\" = " + o_id +
                "), \"C_DELIVERY_CNT\" = \"C_DELIVERY_CNT\" + 1 " +
                " WHERE \"C_W_ID\" = " + c_w_id + " AND " +  "\"C_D_ID\" = " + c_d_id + " AND " +  "\"C_ID\" = " + c_id;
        return jdbcTemplate.update(sql) > 0;
    }

    @Override
    public List<Customer> getCustomersByTopBalance() {
        String sql = "WITH \"tops\" AS(";
        for (int w_id = 1; w_id <= 10; w_id++){
            for(int d_id = 1; d_id <= 10; d_id++){
                if (w_id == 1 & d_id == 1){
                    sql = sql.concat("(SELECT * FROM \"Customer\" WHERE \"C_W_ID\" = " + w_id
                            + " AND \"C_D_ID\" = " + d_id + " ORDER BY \"C_BALANCE\" DESC LIMIT 10)");
                }
                else {
                    sql = sql.concat(" UNION (SELECT * FROM \"Customer\" WHERE \"C_W_ID\" = " + w_id
                            + " AND \"C_D_ID\" = " + d_id + " ORDER BY \"C_BALANCE\" DESC LIMIT 10)");
                }
            }
        }
        sql = sql.concat(") SELECT * FROM \"tops\" ORDER BY \"C_BALANCE\" DESC LIMIT 10");
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
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