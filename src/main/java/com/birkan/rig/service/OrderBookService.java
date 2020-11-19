package com.birkan.rig.service;

import com.birkan.rig.entity.Book;
import com.birkan.rig.entity.Order;
import com.birkan.rig.entity.OrderBook;
import com.birkan.rig.repository.BookRepository;
import com.birkan.rig.repository.OrderBookRepository;
import com.birkan.rig.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderBookService {

    private final OrderBookRepository repository;

    private final OrderRepository orderRepository;

    private final BookRepository bookRepository;

    public void saveOrderBook(Long orderId, Long bookId) {
        Book book = bookRepository.findById(bookId).get();
        Order order = orderRepository.findById(orderId).get();
        repository.save(new OrderBook(order, book));
    }
}
