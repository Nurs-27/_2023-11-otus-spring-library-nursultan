package ru.otus.spring.service;

import ru.otus.spring.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto save(BookDto dto);

    BookDto findById(Long id);

    List<BookDto> findAll();

    void deleteById(Long id);
}
