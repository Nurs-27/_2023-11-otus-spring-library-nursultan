package ru.otus.spring.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.mapper.Mapper;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.service.CommentService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final BookRepository bookRepository;
    private final Mapper mapper;

    @Override
    @Transactional
    public CommentDto save(CommentDto dto) {
        log.info("Starting saving comment: {}", dto);
        final var book = bookRepository.findById(dto.getBookId()).orElseGet(null);
        final var comment = repository.save(mapper.toCommentEntity(dto, book));

        log.info("Successfully created a comment with id: {}", comment.getId());
        return mapper.toCommentDto(comment, book.getId());
    }

    @Override
    public List<CommentDto> findByBookId(Long bookId) {
        log.info("Starting finding comments by bookId: {}", bookId);
        final var book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            log.info("No book found for the ID: {}", bookId);
            return Collections.emptyList();
        }

        final var commentDtos = book.getComments().stream()
                .map(comment -> mapper.toCommentDto(comment, bookId))
                .collect(Collectors.toList());

        log.info("Found comments with size: {} by bookId: {}", commentDtos.size(), bookId);
        return commentDtos;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Starting deleting a comment by id: {}", id);
        repository.deleteById(id);

        log.info("Successfully deleted the comment by id: {}", id);
    }
}
