package com.nus.cs5424.data;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
    private Integer c_w_id;
    private Integer c_d_id;
    private Integer c_id;
    private String c_first;
    private String c_middle;
    private String c_last;
    private String c_street_1;
    private String c_street_2;
    private String c_city;
    private String c_state;
    private String c_zip;
    private String c_phone;
    private Timestamp c_since;
    private String c_credit;
    private BigDecimal c_credit_lim;
    private BigDecimal c_discount;
    private BigDecimal c_balance;
    private Double c_ytd_payment;
    private Integer c_payment_cnt;
    private Integer c_delivery_cnt;
    private String c_data;
}