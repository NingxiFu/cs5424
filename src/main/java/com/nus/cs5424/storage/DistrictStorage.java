/**
 * @(#)DistrictStorage.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

import com.nus.cs5424.data.District;

import java.math.BigDecimal;

/**
 * @author guochenghui
 */
public interface DistrictStorage {

    Integer getNext_O_IDByPrimaryKey(int w_id, int d_id);

    District getDistrictByIdentifier(int w_id, int d_id);

    boolean updateNext_O_ID(int w_id, int d_id, int D_NEXT_ID);

    boolean updateD_YTDByPayment(int w_id, int d_id, BigDecimal payment);
}