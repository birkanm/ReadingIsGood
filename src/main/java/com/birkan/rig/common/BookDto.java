package com.birkan.rig.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookDto {

    private Long pkid;

    private String name;

    private String author;

    private BigDecimal price;

    private Long stock;

}
