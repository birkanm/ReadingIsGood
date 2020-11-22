package com.birkan.rig.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private OrderDto orderDto;

    private List<ShoppingCartDto> orderedBooks;
}
