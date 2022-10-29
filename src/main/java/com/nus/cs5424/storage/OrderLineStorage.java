/**
 * @(#)OrderLineStorage.java, Oct 27, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

import com.nus.cs5424.data.OrderLine;

import java.util.List;

/**
 * @author guochenghui
 */
public interface OrderLineStorage {

    OrderLine add(OrderLine orderLine);

    List<OrderLine> getOrderLinesByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id);
}