package com.birkan.rig.service;

import com.birkan.rig.common.BookDto;
import com.birkan.rig.common.EnumOrderStatus;
import com.birkan.rig.common.OrderDto;
import com.birkan.rig.common.PurchaseBookDto;
import com.birkan.rig.converter.OrderConverterImpl;
import com.birkan.rig.entity.Order;
import com.birkan.rig.exception.NoStockLeftException;
import com.birkan.rig.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderService {

    private final BookService bookService;

    private final OrderRepository repository;

    private final OrderConverterImpl converter;

    private final OrderBookService orderBookService;

    private final CustomerOrderService customerOrderService;

    private final Random random = new Random();

    //todo please refactor!!!
    @Transactional
    public OrderDto saveNewOrder(OrderDto dto) {
        List<PurchaseBookDto> orderedBooks = dto.getOrderedBooks();

        List<Long> bookIds = orderedBooks.stream().map(PurchaseBookDto::getBookId)
                                         .collect(Collectors.toList());

        //check stock
        List<BookDto> booksByBookIds = bookService.getBooksByBookIds(bookIds);
        if (!isStocksAvailable(booksByBookIds, orderedBooks)) {
            throw new NoStockLeftException("No stock left for given order");
        }

        //update book stock
        List<BookDto> collect =
            orderedBooks.stream().map(k -> bookService.sellBook(k.getBookId(), k.getQuantity())).collect(
                Collectors.toList());

        //save order
        dto.setOrderStatus(EnumOrderStatus.COLLECTING);
        dto.setOrderId(random.nextLong());
        dto.setOrderDate(new Date());
        Order savedOrder = repository.save(converter.convertToEntity(dto));
        OrderDto savedOrderDto = converter.convertToDto(savedOrder);

        //save order book
        orderedBooks.forEach(k -> orderBookService.saveOrderBook(savedOrderDto.getPkid(), k.getBookId()));
        savedOrderDto.setOrderedBooks(dto.getOrderedBooks());

        //save customer order
        customerOrderService.saveCustomerOrder(savedOrderDto.getPkid(), dto.getCustomerId());
        savedOrderDto.setCustomerId(dto.getCustomerId());

        return savedOrderDto;
    }

    private boolean isStocksAvailable(List<BookDto> books, List<PurchaseBookDto> bookInOrder) {
        for (BookDto book : books) {
            for (PurchaseBookDto purchaseBookDto : bookInOrder) {
                if (book.getPkid().equals(purchaseBookDto.getBookId()) &&
                    book.getStock() < purchaseBookDto.getQuantity()) {
                    return false;
                }
            }
        }
        return true;
    }
}