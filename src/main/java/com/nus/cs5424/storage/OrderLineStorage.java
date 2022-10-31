/**
 * @(#)OrderLineStorage.java, Oct 27, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

import com.nus.cs5424.data.OrderLine;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @author guochenghui
 */
public interface OrderLineStorage {

    OrderLine add(OrderLine orderLine);

    List<OrderLine> getOrderLinesByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id);

    List<OrderLine> getOrderLinesContainItemSetByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id, Set<Integer> given_i_id_l);

    List<Integer> getOrderLinesContainItemSetByDistrict(int ol_w_id, int ol_d_id, Set<Integer> given_i_id_l);

    boolean updateDelivery_DByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id);

    BigDecimal getSumOfAmountByOneOrder(int ol_w_id, int ol_d_id, int ol_o_id);

    List<Integer> getItemIdsByLastOrders(int ol_w_id, int ol_d_id, int next_o_id, int num_last_orders);

    List<OrderLine> getOrderlinesByPopularItemsInOneOrder(int ol_w_id, int ol_d_id, int ol_o_id);

    Integer getCountByLastOrdersContainsTheItem(int ol_w_id, int ol_d_id, int ol_i_id, int next_o_id,  int num_last_orders);
}