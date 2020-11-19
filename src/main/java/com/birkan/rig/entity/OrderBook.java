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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "PKID", name = "FK_ORDERID")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "PKID", name = "FK_BOOKID")
    private Book book;

    public OrderBook(Order order, Book book) {
        this.order = order;
        this.book = book;
    }

}
