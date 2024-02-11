package ru.otus.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.mapper.Mapper;
import ru.otus.spring.repository.CommentRepository;

import java.util.List;

public interface CommentService {

    CommentDto save(CommentDto dto);

    List<CommentDto> findByBookId(Long bookId);

    void deleteById(Long id);
}
