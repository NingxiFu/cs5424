/**
 * @(#)DistrictStorageImpl.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage.db;

import com.google.common.collect.ImmutableMap;
import com.nus.cs5424.storage.BaseStorage;
import com.nus.cs5424.storage.DistrictStorage;
import org.springframework.stereotype.Repository;

/**
 * @author guochenghui
 */
@Repository
public class DistrictStorageImpl extends BaseStorage implements DistrictStorage {

    private static final String TABLE = "\"District\"";

    @Override
    public Integer getNext_O_IDByPrimaryKey(int w_id, int d_id) {
        String sql = "SELECT \"D_NEXT_O_ID\" FROM " + TABLE + " WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public boolean updateNext_O_ID(int w_id, int d_id) {
        String sql = "UPDATE " + TABLE + " SET \"D_NEXT_O_ID\" = \"D_NEXT_O_ID\" + 1 WHERE \"D_W_ID\" = " + w_id + " AND " +  "\"D_ID\" = " + d_id;
        return jdbcTemplate.update(sql) > 0;
    }
}