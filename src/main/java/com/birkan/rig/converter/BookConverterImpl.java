package com.birkan.rig.converter;

import com.birkan.rig.common.BookDto;
import com.birkan.rig.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookConverterImpl extends BaseConverter<BookDto, Book> {

    @Override
    public void convertInEntity(BookDto dto, Book entity) {
        entity.setAuthor(dto.getAuthor());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());
    }

    @Override
    public Book convertToEntity(BookDto dto) {
        Book book = new Book();
        convertInEntity(dto, book);
        return book;
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
}
