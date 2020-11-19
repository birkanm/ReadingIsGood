package com.birkan.rig.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseBookDto {

    private Long bookId;

    private Integer quantity;
}
