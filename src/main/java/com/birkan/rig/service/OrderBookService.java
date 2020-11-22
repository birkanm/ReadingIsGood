package com.birkan.rig.service;

import com.birkan.rig.common.BookDto;
import com.birkan.rig.common.OrderBookDto;
import com.birkan.rig.common.OrderDto;
import com.birkan.rig.converter.BookConverterImpl;
import com.birkan.rig.converter.OrderConverterImpl;
import com.birkan.rig.entity.Book;
import com.birkan.rig.entity.Order;
import com.birkan.rig.entity.OrderBook;
import com.birkan.rig.repository.BookRepository;
import com.birkan.rig.repository.OrderBookRepository;
import com.birkan.rig.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderBookService {

    private final OrderBookRepository repository;

    private final OrderRepository orderRepository;

    private final BookRepository bookRepository;

    private final BookConverterImpl bookConverter;

    private final OrderConverterImpl orderConverter;

    public List<OrderBookDto> findBooksByOrderId(Long orderId) {
        List<OrderBook> orderBookList = repository.findBooksByOrderId(orderId);

        if (orderBookList.size() == 0) {
            return Collections.emptyList();
        }

        OrderDto orderDto = orderConverter.convertToDto(orderBookList.get(0).getOrder());

        return orderBookList.stream().map(k -> {
            BookDto bookDto = bookConverter.convertToDto(k.getBook());
            return new OrderBookDto(orderDto, bookDto, k.getQuantity());
        }).collect(Collectors.toList());
    }

    public void saveOrderBook(Long orderId, Long bookId, Integer quantity) {
        Order order = orderRepository.findById(orderId).get();
        Book book = bookRepository.findById(bookId).get();
        repository.save(new OrderBook(order, book, quantity));
    }
}
