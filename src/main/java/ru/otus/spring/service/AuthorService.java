package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.mapper.Mapper;
import ru.otus.spring.repository.AuthorRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;
    private final Mapper mapper;

    @Transactional
    public Long save(AuthorDto dto) {
        log.info("Starting saving author: {}", dto);
        final var authorId = repository.insert(mapper.toAuthorEntity(dto));

        log.info("Successfully created a author with id: {}", authorId);
        return authorId;
    }
}
