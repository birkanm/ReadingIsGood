package com.birkan.rig.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_BOOK")
@Data
@NoArgsConstructor
public class OrderBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PKID")
    private Long pkid;

    @ManyToOne
    @JoinColumn(referencedColumnName = "PKID", name = "FK_ORDERID")
    private Order order;

    @ManyToOne
    @JoinColumn(referencedColumnName = "PKID", name = "FK_BOOKID")
    private Book book;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public OrderBook(Order order, Book book, Integer quantity) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
    }
}

