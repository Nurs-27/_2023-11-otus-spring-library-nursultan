package ru.otus.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.mapper.Mapper;
import ru.otus.spring.repository.BookRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final Mapper mapper;

    @Transactional
    public BookDto save(BookDto dto) {
        log.info("Starting saving book: {}", dto);
        final var book = repository.save(mapper.toBookEntity(dto));

        log.info("Successfully created a book with id: {}", book.getId());
        return mapper.toBookDto(book);
    }

    public BookDto findById(Long id) {
        log.info("Starting finding book by id: {}", id);
        final var book = repository.findById(id).orElseThrow();

        log.info("Book with id: {} result: {}", id, book);
        return mapper.toBookDto(book);
    }

    public List<Book> getAll() {
        log.info("Starting getting all books");
        final var books = repository.findAll();

        log.info("Successfully found books with size: {}", books.size());
        return books;
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Starting deleting book by id: {}", id);
        repository.deleteById(id);

        log.info("Successfully deleted book by id: {}", id);
    }
}
