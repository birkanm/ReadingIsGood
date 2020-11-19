package com.birkan.rig.service;

import com.birkan.rig.common.BookDto;
import com.birkan.rig.converter.BookConverterImpl;
import com.birkan.rig.entity.Book;
import com.birkan.rig.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    private final BookConverterImpl converter;

    public List<BookDto> getBooksByBookIds(List<Long> idList) {
        List<Book> books = repository.fetchCustomersByOrgId(idList);
        return converter.toDtoList(books);
    }

    public BookDto sellBook(Long bookId, Integer soldQuantity) {
        Book book = repository.findById(bookId).get();
        book.setStock(book.getStock() - soldQuantity);
        return converter.convertToDto(repository.save(book));
    }
}
