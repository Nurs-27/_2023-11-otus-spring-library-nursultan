package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

@Repository
@RequiredArgsConstructor
public class GenreRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String SQL_INSERT = "INSERT INTO genre (name) VALUES (:name)";

    public Long insert(Genre genre) {
        final var keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                SQL_INSERT,
                new MapSqlParameterSource("name", genre.getName()),
                keyHolder,
                new String[]{"id"}
        );
        return keyHolder.getKey().longValue();
    }

}