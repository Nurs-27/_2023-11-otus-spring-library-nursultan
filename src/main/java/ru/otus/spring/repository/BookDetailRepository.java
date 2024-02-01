package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookDetail;
import ru.otus.spring.repository.rowmapper.BookDetailRowMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDetailRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String SQL_FIND_BY_ID = """
                SELECT b.id, b.title, b.author_id, b.genre_id, b.published_at
                FROM book as b
                INNER JOIN author as a on b.author_id = a.id
                INNER JOIN genre as g on b.genre_id = g.id
                WHERE b.id = :id
            """;

    private static final String SQL_FIND_ALL = """
                SELECT b.id, b.title, b.author_id, b.genre_id, b.published_at,
                a.first_name, a.last_name, a.birth_date,
                g.id, g.name
                FROM book as b
                INNER JOIN author as a on b.author_id = a.id
                INNER JOIN genre as g on b.genre_id = g.id
            """;

    private static final RowMapper<BookDetail> ROW_MAPPER = new BookDetailRowMapper();

    public Optional<BookDetail> findById(Long id) {
        return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(
                SQL_FIND_BY_ID,
                new MapSqlParameterSource(
                        "id", id
                ),
                ROW_MAPPER
        ));
    }

    public List<BookDetail> findAll() {
        return namedParameterJdbcOperations.query(
                SQL_FIND_ALL,
                ROW_MAPPER
        );
    }
}
