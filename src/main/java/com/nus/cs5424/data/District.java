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
@Table(value = "District")
public class District implements Persistable<Integer> {
    @Id
    @Column("D_W_ID")
    private int id; //d_w_id
    @Column("D_ID")
    private String d_id;
    @Column("D_NAME")
    private String d_name;
    @Column("D_STREET_1")
    private String d_street_1;
    @Column("D_STREET_2")
    private String d_street_2;
    @Column("D_CITY")
    private String d_city;
    @Column("D_STATE")
    private String d_state;
    @Column("D_ZIP")
    private String d_zip;
    @Column("D_TAX")
    private BigDecimal d_tax;
    @Column("D_YTD")
    private BigDecimal d_ytd;
    @Column("D_NEXT_O_ID")
    private int d_next_o_id;

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
        return "District{" +
                "d_w_id=" + id +
                ", d_id='" + d_id + '\'' +
                ", d_name='" + d_name + '\'' +
                ", d_street_1='" + d_street_1 + '\'' +
                ", d_street_2='" + d_street_2 + '\'' +
                ", d_city='" + d_city + '\'' +
                ", d_state='" + d_state + '\'' +
                ", d_zip'=" + d_zip + '\'' +
                ", d_tax=" + d_tax +
                ", d_ytd=" + d_ytd +
                ", d_next_o_id=" + d_next_o_id +
                ", isInsert=" + isInsert +
                '}';
    }
}