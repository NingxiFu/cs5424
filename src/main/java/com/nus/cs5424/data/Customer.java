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
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "Customer")
public class Customer implements Persistable<Integer> {

    @Id
    @Column("C_W_ID")
    private int id; //c_w_id
    @Column("C_D_ID")
    private int c_d_id;
    @Column("C_ID")
    private int c_id;
    @Column("C_FIRST")
    private String c_first;
    @Column("C_MIDDLE")
    private String c_middle;
    @Column("C_LAST")
    private String c_last;
    @Column("C_STREET_1")
    private String c_street_1;
    @Column("C_STREET_2")
    private String c_street_2;
    @Column("C_CITY")
    private String c_city;
    @Column("C_STATE")
    private String c_state;
    @Column("C_ZIP")
    private String c_zip;
    @Column("C_PHONE")
    private String c_phone;
    @Column("C_SINCE")
    private Timestamp c_since;
    @Column("C_CREDIT")
    private String c_credit;
    @Column("C_CREDIT_LIM")
    private BigDecimal c_credit_lim;
    @Column("C_DISCOUNT")
    private BigDecimal c_discount;
    @Column("C_BALANCE")
    private BigDecimal c_balance;
    @Column("C_YTD_PAYMENT")
    private double c_ytd_payment;
    @Column("C_PAYMENT_CNT")
    private int c_payment_cnt;
    @Column("C_DELIVERY_CNT")
    private int c_delivery_cnt;
    @Column("C_DATA")
    private String c_data;

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
        return "Customer{" +
                "c_w_id=" + id +
                ", c_d_id=" + c_d_id +
                ", c_id=" + c_id +
                ", c_first='" + c_first + '\'' +
                ", c_middle='" + c_middle + '\'' +
                ", c_last='" + c_last + '\'' +
                ", c_street_1='" + c_street_1 + '\'' +
                ", c_street_2='" + c_street_2 + '\'' +
                ", c_city='" + c_city + '\'' +
                ", c_state='" + c_state + '\'' +
                ", c_zip'=" + c_zip + '\'' +
                ", c_phone'=" + c_phone + '\'' +
                ", c_since=" + c_since +
                ", c_credit'=" + c_credit + '\'' +
                ", c_credit_lim=" + c_credit_lim +
                ", c_discount=" + c_discount +
                ", c_balance=" + c_balance +
                ", c_ytd_payment=" + c_ytd_payment +
                ", c_payment_cnt=" + c_payment_cnt +
                ", c_delivery_cnt=" + c_delivery_cnt +
                ", c_data='" + c_data + '\'' +
                ", isInsert=" + isInsert +
                '}';
    }
}