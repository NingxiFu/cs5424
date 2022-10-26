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
@Table(value = "Order")
public class Order implements Persistable<Integer> {

    @Id
    @Column("O_W_ID")
    private int id; //o_w_id
    @Column("O_D_ID")
    private int o_d_id;
    @Column("O_ID")
    private int o_id;
    @Column("O_C_ID")
    private int o_c_id;
    @Column("O_CARRIER_ID")
    private int o_carrier_id;
    @Column("O_OL_CNT")
    private BigDecimal o_ol_cnt;
    @Column("O_ALL_LOCAL")
    private BigDecimal o_all_local;
    @Column("O_ENTRY_D")
    private Timestamp o_entry_d;

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
        return "Order{" +
                "o_w_id=" + id +
                ", o_d_id=" + o_d_id +
                ", o_id=" + o_id +
                ", o_c_id=" + o_c_id +
                ", o_carrier_id=" + o_carrier_id +
                ", o_ol_cnt=" + o_ol_cnt +
                ", o_all_local=" + o_all_local +
                ", o_entry_d=" + o_entry_d +
                ", isInsert=" + isInsert +
                '}';
    }
}