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
    private int c_w_id;
    private int c_d_id;
    private int c_id;
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
    private double c_ytd_payment;
    private int c_payment_cnt;
    private int c_delivery_cnt;
    private String c_data;
}