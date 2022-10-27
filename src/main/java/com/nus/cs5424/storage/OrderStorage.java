/**
 * @(#)OrderStorage.java, Oct 26, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.storage;

import com.nus.cs5424.data.Order;

/**
 * @author guochenghui
 */
public interface OrderStorage {

    Order add(Order order);
}