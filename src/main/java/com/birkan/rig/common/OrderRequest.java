package com.birkan.rig.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    @Positive(message = "Customer id must be a positive number")
    private Long customerId;

    @NotNull(message = "At least one book must be added to shopping cart")
    @Valid
    private List<ShoppingCartDto> orderedBooks;

}
