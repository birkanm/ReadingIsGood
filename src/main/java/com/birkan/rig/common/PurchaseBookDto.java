package com.birkan.rig.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseBookDto {

    private BookDto bookDto;

    private Integer quantity;
}
