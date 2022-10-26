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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "Item")
public class Item implements Persistable<Integer> {

    @Id
    @Column("I_ID")
    private int id; //i_id
    @Column("I_NAME")
    private String i_name;
    @Column("I_PRICE")
    private BigDecimal i_price;
    @Column("I_IM_ID")
    private int i_im_id;
    @Column("I_DATA")
    private String i_data;

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
        return "Item{" +
                "i_id=" + id +
                ", i_name='" + i_name + '\'' +
                ", i_price=" + i_price +
                ", i_im_id=" + i_im_id +
                ", i_data='" + i_data + '\'' +
                ", isInsert=" + isInsert +
                '}';
    }
}