/**
 * @(#)Employee.java, Oct 24, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nus.cs5424.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * @author guochenghui
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "Warehouse")
public class Warehouse implements Persistable<Integer> {

    @Id
    @Column("W_ID")
    private int id;
    @Column("W_NAME")
    private String w_name;
    @Column("W_STREET_1")
    private String w_street_1;
    @Column("W_STREET_2")
    private String w_street_2;
    @Column("W_CITY")
    private String w_city;
    @Column("W_STATE")
    private String w_state;
    @Column("W_ZIP")
    private String w_zip;
    @Column("W_TAX")
    private BigDecimal w_tax;
    @Column("W_YTD")
    private BigDecimal w_ytd;

    @Transient
    private Boolean isInsert = true;

    @Override
    public Integer getId() {
        return id;
    }



    @Override
    public boolean isNew() {
        return isInsert;
    }


    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", w_name='" + w_name + '\'' +
                ", w_street_1='" + w_street_1 + '\'' +
                ", w_street_2='" + w_street_2 + '\'' +
                ", w_city='" + w_city + '\'' +
                ", w_state='" + w_state + '\'' +
                ", w_zip='" + w_zip + '\'' +
                ", w_tax=" + w_tax +
                ", w_ytd=" + w_ytd +
                ", isInsert=" + isInsert +
                '}';
    }
}