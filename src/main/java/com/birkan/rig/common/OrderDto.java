package com.birkan.rig.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long pkid;

    private Long orderId;

    private Date orderDate;

    private EnumOrderStatus orderStatus;

    private Date completeDate;

    private Long customerId;

    private List<PurchaseBookDto> orderedBooks;

}
