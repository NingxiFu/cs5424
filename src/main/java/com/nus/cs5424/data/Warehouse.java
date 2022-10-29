/**
 * @(#)Employee.java, Oct 24, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * @author funingxi
 */

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse{

    private Integer w_id;
    private String w_name;
    private String w_street_1;
    private String w_street_2;
    private String w_city;
    private String w_state;
    private String w_zip;
    private BigDecimal w_tax;
    private BigDecimal w_ytd;

}