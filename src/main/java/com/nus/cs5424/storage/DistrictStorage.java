/**
 * @(#)DistrictStorage.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

/**
 * @author guochenghui
 */
public interface DistrictStorage {

    Integer getNext_O_IDByPrimaryKey(int w_id, int d_id);

    boolean updateNext_O_ID(int w_id, int d_id);

}