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
@Table(value = "OrderLine")
public class OrderLine implements Persistable<Integer> {

    @Id
    @Column("OL_W_ID")
    private int id; //ol_w_id
    @Column("OL_D_ID")
    private int ol_d_id;
    @Column("OL_O_ID")
    private int ol_o_id;
    @Column("OL_NUMBER")
    private int ol_number;
    @Column("OL_I_ID")
    private int ol_i_id;
    @Column("OL_DELIVERY_D")
    private Timestamp ol_delivery_d;
    @Column("OL_AMOUNT")
    private BigDecimal ol_amount;
    @Column("OL_SUPPLY_W_ID")
    private int ol_supply_w_id;
    @Column("OL_QUANTITY")
    private BigDecimal ol_quantity;
    @Column("OL_DIST_INFO")
    private String ol_dist_info;


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
        return "OrderLine{" +
                "ol_w_id=" + id +
                ", ol_d_id=" + ol_d_id +
                ", ol_o_id=" + ol_o_id +
                ", ol_number=" + ol_number +
                ", ol_i_id=" + ol_i_id +
                ", ol_delivery_d=" + ol_delivery_d +
                ", ol_amount=" + ol_amount +
                ", ol_supply_w_id=" + ol_supply_w_id +
                ", ol_quantity=" + ol_quantity +
                ", ol_dist_info=" + ol_dist_info + '\'' +
                ", isInsert='" + isInsert +
                '}';
    }
}