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
@Table(value = "Stock")
public class Stock implements Persistable<Integer> {

    @Id
    @Column("S_W_ID")
    private int id; //s_w_id
    @Column("S_I_ID")
    private int s_i_id;
    @Column("S_QUANTITY")
    private BigDecimal s_quantity;
    @Column("S_YTD")
    private BigDecimal s_ytd;
    @Column("S_ORDER_CNT")
    private int s_order_cnt;
    @Column("S_REMOTE_CNT")
    private int s_remote_cnt;
    @Column("S_DIST_01")
    private String s_dist_01;
    @Column("S_DIST_02")
    private String s_dist_02;
    @Column("S_DIST_03")
    private String s_dist_03;
    @Column("S_DIST_04")
    private String s_dist_04;
    @Column("S_DIST_05")
    private String s_dist_05;
    @Column("S_DIST_06")
    private String s_dist_06;
    @Column("S_DIST_07")
    private String s_dist_07;
    @Column("S_DIST_08")
    private String s_dist_08;
    @Column("S_DIST_09")
    private String s_dist_09;
    @Column("S_DIST_10")
    private String s_dist_10;
    @Column("S_DATA")
    private String s_data;


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
        return "Stock{" +
                "s_w_id=" + id +
                ", s_i_id=" + s_i_id +
                ", s_quantity=" + s_quantity +
                ", s_ytd=" + s_ytd +
                ", s_order_cnt=" + s_order_cnt +
                ", s_remote_cnt=" + s_remote_cnt +
                ", s_dist_01='" + s_dist_01 + '\'' +
                ", s_dist_02='" + s_dist_02 + '\'' +
                ", s_dist_03='" + s_dist_03 + '\'' +
                ", s_dist_04='" + s_dist_04 + '\'' +
                ", s_dist_05='" + s_dist_05 + '\'' +
                ", s_dist_06='" + s_dist_06 + '\'' +
                ", s_dist_07='" + s_dist_07 + '\'' +
                ", s_dist_08='" + s_dist_08 + '\'' +
                ", s_dist_09='" + s_dist_09 + '\'' +
                ", s_dist_10='" + s_dist_10 + '\'' +
                ", s_data='" + s_data + '\'' +
                ", isInsert='" + isInsert +
                '}';
    }
}