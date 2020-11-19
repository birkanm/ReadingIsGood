package com.birkan.rig.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_ORDER")
@Data
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PKID")
    private Long pkid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "PKID", name = "FK_CUSTOMERID")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "PKID", name = "FK_ORDERID")
    private Order order;

    public CustomerOrder(Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
    }
}
