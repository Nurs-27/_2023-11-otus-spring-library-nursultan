package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookUpdatingRequest;
import ru.otus.spring.service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public Long create(@RequestBody BookDto dto) {
        return bookService.create(dto);
    }

    @GetMapping("{id}")
    public BookDto findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/all")
    public List<BookDto> findAll() {
        return bookService.getAll();
    }

    @PutMapping
    public void update(@RequestBody BookUpdatingRequest request) {
        bookService.update(request);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
