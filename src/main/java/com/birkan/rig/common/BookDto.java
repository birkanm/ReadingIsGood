package com.birkan.rig.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BookDto {

    @NotNull
    @Min(value = 1L, message = "Book id must be positive number")
    private Long pkid;

    private String name;

    private String author;

    private BigDecimal price;

    private Long stock;

}
