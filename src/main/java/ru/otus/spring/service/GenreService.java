package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.GenreDto;
import ru.otus.spring.mapper.Mapper;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.GenreRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository repository;
    private final Mapper mapper;

    @Transactional
    public Long save(GenreDto dto) {
        log.info("Starting saving genre: {}", dto);
        final var genreId = repository.insert(mapper.toGenreEntity(dto));

        log.info("Successfully created a genre with id: {}", genreId);
        return genreId;
    }
}
