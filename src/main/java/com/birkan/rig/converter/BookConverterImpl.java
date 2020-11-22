package com.birkan.rig.converter;

import com.birkan.rig.common.BookDto;
import com.birkan.rig.entity.Book;
import com.birkan.rig.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookConverterImpl implements BaseConverter<BookDto, Book> {

    private final BookRepository bookRepository;

    @Override
    public Book convertToEntity(BookDto dto) {
        Book entity = getOrCreateEmptyEntity(dto.getPkid());
        entity.setAuthor(dto.getAuthor());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());
        return entity;
    }

    @Override
    public BookDto convertToDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setAuthor(entity.getAuthor());
        dto.setName(entity.getName());
        dto.setPkid(entity.getPkid());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());
        return dto;
    }

    private Book getOrCreateEmptyEntity(Long entityId) {
        if (Objects.nonNull(entityId)) {
            Optional<Book> optBook = bookRepository.findById(entityId);
            if (optBook.isPresent()) {
                return optBook.get();
            }
        }
        return new Book();
    }
}
