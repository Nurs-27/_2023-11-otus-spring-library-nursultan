package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookUpdatingRequest;
import ru.otus.spring.mapper.Mapper;
import ru.otus.spring.repository.BookDetailRepository;
import ru.otus.spring.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookDetailRepository bookDetailRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final Mapper mapper;

    @Transactional
    public Long create(BookDto request) {
        log.info("Starting creating book: {}", request);
        final var authorId = authorService.save(request.getAuthorDto());
        final var genreId = genreService.save(request.getGenreDto());

        final var bookId = bookRepository.insert(mapper.toBookEntity(request, authorId, genreId));

        log.info("Successfully created a book with id: {}", bookId);
        return bookId;
    }

    @Transactional
    public void update(BookUpdatingRequest request) {
        log.info("Starting updating book: {}", request);
        bookRepository.update(mapper.toBookEntity(request));

        log.info("Successfully updated a book with id: {}", request.getId());
    }

    public BookDto findById(Long id) {
        log.info("Starting finding book by id: {}", id);
        final var bookDetail = bookDetailRepository.findById(id)
                .orElseThrow();

        log.info("Book with id: {} result: {}", id, bookDetail);
        return mapper.toBookDto(bookDetail);
    }

    public List<BookDto> getAll() {
        log.info("Starting getting all books");
        final var books = bookDetailRepository.findAll();

        log.info("Successfully found books with size: {}", books.size());
        return books
                .stream()
                .map(mapper::toBookDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Starting deleting book by id: {}", id);
        bookRepository.deleteById(id);

        log.info("Successfully deleted book by id: {}", id);
    }
}
