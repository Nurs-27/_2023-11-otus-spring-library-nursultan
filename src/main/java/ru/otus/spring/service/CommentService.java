package ru.otus.spring.service;

import ru.otus.spring.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto save(CommentDto dto);

    List<CommentDto> findByBookId(Long bookId);

    void deleteById(Long id);
}
