package com.birkan.rig.repository;

import com.birkan.rig.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE customer.pkid = :aLong")
    List<Order> findOrdersByCustomerId(Long aLong);

}
