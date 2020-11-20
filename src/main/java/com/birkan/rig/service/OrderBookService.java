package com.birkan.rig.service;

import com.birkan.rig.common.BookDto;
import com.birkan.rig.common.PurchaseOrderDto;
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

    public void saveOrderBook(PurchaseOrderDto orderDto, BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getPkid()).get();
        Order order = orderRepository.findById(orderDto.getPkid()).get();
        repository.save(new OrderBook(order, book));
    }
}
