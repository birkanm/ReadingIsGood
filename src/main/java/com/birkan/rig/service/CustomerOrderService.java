package com.birkan.rig.service;

import com.birkan.rig.entity.Customer;
import com.birkan.rig.entity.CustomerOrder;
import com.birkan.rig.entity.Order;
import com.birkan.rig.repository.CustomerOrderRepository;
import com.birkan.rig.repository.CustomerRepository;
import com.birkan.rig.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerOrderService {

    private final CustomerRepository customerRepository;

    private final OrderRepository orderRepository;

    private final CustomerOrderRepository repository;

    public void saveCustomerOrder(Long orderId, Long customerId) {
        Order order = orderRepository.findById(orderId).get();
        Customer customer = customerRepository.findById(customerId).get();

        repository.save(new CustomerOrder(customer, order));
    }

}
