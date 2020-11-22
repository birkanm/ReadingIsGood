package com.birkan.rig.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBookDto {

    private Long pkid;

    private OrderDto order;

    private BookDto book;

    private Integer quantity;

    public OrderBookDto(OrderDto order, BookDto book, Integer quantity) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
    }
}
