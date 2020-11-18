package com.birkan.rig.repository;

import com.birkan.rig.entity.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

}
