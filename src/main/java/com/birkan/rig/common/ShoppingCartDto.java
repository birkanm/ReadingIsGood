package com.birkan.rig.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {

    @NotNull(message = "At least one book should be in Cart")
    @Valid
    private BookDto bookDto;

    @NotNull(message = "Quantity value must exists")
    @Min(value = 1, message = "Quantity value must be positive")
    private Integer quantity;
}
