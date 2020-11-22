package com.birkan.rig.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long pkid;

    private Long orderId;

    private CustomerDto customer;

    private Date orderDate;

    private EnumOrderStatus orderStatus;

    private Date completeDate;

}
