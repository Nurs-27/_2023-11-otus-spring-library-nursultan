package ru.otus.spring.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.mapper.Mapper;
import ru.otus.spring.repository.CommentRepository;
import ru.otus.spring.service.CommentService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final Mapper mapper;

    @Transactional
    public CommentDto save(CommentDto dto) {
        log.info("Starting saving comment: {}", dto);
        final var comment = repository.save(mapper.toCommentEntity(dto));

        log.info("Successfully created a comment with id: {}", comment.getId());
        return mapper.toCommentDto(comment);
    }

    public List<CommentDto> findByBookId(Long bookId) {
        log.info("Starting finding comments by bookId: {}", bookId);
        final var commentDtos = repository.findByBookId(bookId)
                .stream()
                .map(mapper::toCommentDto)
                .toList();

        log.info("Found comments with size: {} by bookId: {}", commentDtos.size(), bookId);
        return commentDtos;
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Starting deleting a comment by id: {}", id);
        repository.deleteById(id);

        log.info("Successfully deleted the comment by id: {}", id);
    }
}
