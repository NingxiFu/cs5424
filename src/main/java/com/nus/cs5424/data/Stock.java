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
public class Stock{

    private Integer s_w_id;
    private Integer s_i_id;
    private BigDecimal s_quantity;
    private BigDecimal s_ytd;
    private Integer s_order_cnt;
    private Integer s_remote_cnt;
    private String s_dist_01;
    private String s_dist_02;
    private String s_dist_03;
    private String s_dist_04;
    private String s_dist_05;
    private String s_dist_06;
    private String s_dist_07;
    private String s_dist_08;
    private String s_dist_09;
    private String s_dist_10;
    private String s_data;

}