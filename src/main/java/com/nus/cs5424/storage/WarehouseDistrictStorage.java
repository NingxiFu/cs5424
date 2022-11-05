package com.nus.cs5424.storage;

import com.nus.cs5424.data.WarehouseDistrict;

import java.math.BigDecimal;


public interface WarehouseDistrictStorage {

    Integer getNext_O_IDByPrimaryKey(int w_id, int d_id);

    WarehouseDistrict getWarehouseDistrictByIdentifier(int w_id, int d_id);

    WarehouseDistrict getWarehouseDistrictOnlyReadByIdentifier(int w_id, int d_id);

    boolean updateNext_O_ID(int w_id, int d_id, int D_NEXT_ID);

    WarehouseDistrict updateD_YTDByPayment(int w_id, int d_id, BigDecimal payment);

    boolean updateW_YTDByPayment(int w_id, BigDecimal payment);
}