package com.nus.cs5424.data;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private int i_id; //i_id
    private String i_name;
    private BigDecimal i_price;
    private int i_im_id;
    private String i_data;
}