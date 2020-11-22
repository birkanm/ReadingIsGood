package com.birkan.rig.service;

import com.birkan.rig.common.*;
import com.birkan.rig.converter.OrderConverterImpl;
import com.birkan.rig.entity.Order;
import com.birkan.rig.exception.NoStockLeftException;
import com.birkan.rig.exception.OrderNotFoundException;
import com.birkan.rig.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderService {

    private final BookService bookService;

    private final OrderRepository repository;

    private final OrderConverterImpl converter;

    private final OrderBookService orderBookService;

    private final CustomerService customerService;

    private final Logger logger;

    @Transactional
    public OrderDto saveNewOrder(OrderRequest request) {
        List<ShoppingCartDto> orderedBooks = request.getOrderedBooks();

        //check customer
        customerService.findById(request.getCustomerId());

        List<Long> bookIds = orderedBooks.stream().map(ShoppingCartDto::getBookDto).map(BookDto::getPkid)
                                         .collect(Collectors.toList());

        //check stock
        List<BookDto> booksByBookIds = bookService.getBooksByBookIds(bookIds);
        if (!isStocksAvailable(booksByBookIds, orderedBooks)) {
            throw new NoStockLeftException("No stock left for given order");
        }

        //save order
        Order savedOrder = repository.save(converter.convertToEntity(getOrderDtoFromRequest(request)));
        OrderDto orderDto = converter.convertToDto(savedOrder);

        //update book stock
        orderedBooks.forEach(k -> bookService.sellBook(k.getBookDto(), k.getQuantity()));

        logger.debug("{} book(s) sold with Order-ID: {}",
            request.getOrderedBooks().stream().mapToInt(ShoppingCartDto::getQuantity).sum(),
            orderDto.getOrderId());

        //save order book
        request.getOrderedBooks().forEach(
            k -> orderBookService.saveOrderBook(orderDto.getPkid(), k.getBookDto().getPkid(), k.getQuantity()));

        return orderDto;
    }

    public OrderDetailDto getOrderDetailByOrderId(Long orderId) {
        Optional<Order> optOrder = repository.findById(orderId);
        if (!optOrder.isPresent()) {
            throw new OrderNotFoundException(String.format("Unable to find order: %d", orderId));
        }

        OrderDetailDto detailDto = new OrderDetailDto();
        OrderDto orderDto = converter.convertToDto(optOrder.get());
        detailDto.setOrderDto(orderDto);

        List<OrderBookDto> orderBooks = orderBookService.findBooksByOrderId(orderId);

        detailDto.setOrderedBooks(orderBooks.stream().map(k -> new ShoppingCartDto(k.getBook(), k.getQuantity()))
                                            .collect(Collectors.toList()));

        return detailDto;
    }

    public List<OrderDto> getOrdersByCustomerId(Long orderId) {
        List<Order> customerOrders = repository.findOrdersByCustomerId(orderId);
        return converter.toDtoList(customerOrders);
    }

    private OrderDto getOrderDtoFromRequest(OrderRequest request) {
        OrderDto dto = new OrderDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setPkid(request.getCustomerId());
        dto.setCustomer(customerDto);
        dto.setOrderStatus(EnumOrderStatus.COLLECTING);
        dto.setOrderId(System.currentTimeMillis());
        dto.setOrderDate(new Date());
        return dto;
    }

    private boolean isStocksAvailable(List<BookDto> books, List<ShoppingCartDto> bookInOrder) {
        for (BookDto book : books) {
            for (ShoppingCartDto shoppingCartDto : bookInOrder) {
                if (book.getPkid().equals(shoppingCartDto.getBookDto().getPkid()) &&
                    book.getStock() < shoppingCartDto.getQuantity()) {
                    return false;
                }
            }
        }
        return true;
    }
}
