package com.nus.cs5424.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine{

    public OrderLine(Integer ol_number, Integer ol_i_id, Integer ol_supply_w_id, BigDecimal ol_quantity) {
        this.ol_number = ol_number;
        this.ol_i_id = ol_i_id;
        this.ol_supply_w_id = ol_supply_w_id;
        this.ol_quantity = ol_quantity;
    }

    private Integer ol_w_id;
    private Integer ol_d_id;
    private Integer ol_o_id;
    private Integer ol_number;
    private Integer ol_i_id;
    private Timestamp ol_delivery_d;
    private BigDecimal ol_amount;
    private Integer ol_supply_w_id;
    private BigDecimal ol_quantity;
    private String ol_dist_info;


}