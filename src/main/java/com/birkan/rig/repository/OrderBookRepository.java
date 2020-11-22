package com.birkan.rig.repository;

import com.birkan.rig.entity.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

    @Query("SELECT b FROM OrderBook b WHERE order.pkid = :orderId")
    List<OrderBook> findBooksByOrderId(Long orderId);
}
