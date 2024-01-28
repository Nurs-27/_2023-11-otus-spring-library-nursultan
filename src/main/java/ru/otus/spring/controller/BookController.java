package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookDto save(@RequestBody BookDto dto) {
        return bookService.save(dto);
    }

    @GetMapping("{id}")
    public BookDto findById(@PathVariable Long id) {
        final var result = bookService.findById(id);

        return result;
    }

    @GetMapping("/all")
    public List<Book> findAll() {
        return bookService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
