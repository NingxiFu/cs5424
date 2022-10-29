package com.nus.cs5424.data;

import lombok.*;
import org.springframework.data.relational.core.sql.In;

import java.math.BigDecimal;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class District {
    private Integer d_w_id;
    private String d_id;
    private String d_name;
    private String d_street_1;
    private String d_street_2;
    private String d_city;
    private String d_state;
    private String d_zip;
    private BigDecimal d_tax;
    private BigDecimal d_ytd;
    private Integer d_next_o_id;
}