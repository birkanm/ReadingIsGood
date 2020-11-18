package com.birkan.rig.entity;

import com.birkan.rig.common.EnumOrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "BOOK_ORDER")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PKID")
    private Long pkid;

    @Column(name = "ORDERID")
    private Long orderId;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS")
    private EnumOrderStatus orderStatus;

    @Column(name = "COMPLETE_DATE")
    private Date completeDate;

}
