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
public class Order {

    private int o_w_id;
    private int o_d_id;
    private int o_id;
    private int o_c_id;
    private int o_carrier_id;
    private BigDecimal o_ol_cnt;
    private BigDecimal o_all_local;
    private Timestamp o_entry_d;

}