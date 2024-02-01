package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String SQL_INSERT = """
        INSERT INTO author (first_name, last_name, birth_date)
            VALUES (:first_name, :last_name, :birth_date)
    """;

    public Long insert(Author author) {
        final var keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                SQL_INSERT,
                new MapSqlParameterSource()
                        .addValue("first_name", author.getFirstName())
                        .addValue("last_name", author.getLastName())
                        .addValue("birth_date", author.getBirthDate()),
                keyHolder,
                new String[]{"id"}
        );
        return keyHolder.getKey().longValue();
    }

}