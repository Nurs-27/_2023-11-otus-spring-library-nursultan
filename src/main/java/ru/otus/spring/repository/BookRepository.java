package ru.otus.spring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private static final String SQL_INSERT = """
                INSERT INTO book (title, author_id, genre_id, published_at)
                    VALUES (:title, :author_id, :genre_id, :published_at)
            """;

    private static final String SQL_UPDATE = """
                UPDATE book 
                SET title = :title,
                    author_id = :author_id,
                    genre_id = :genre_id,
                    published_at = :published_at
                WHERE id = :id
            """;

    private static final String SQL_DELETE = "DELETE FROM book WHERE id = :id";

    public Long insert(Book book) {
        final var keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                SQL_INSERT,
                new MapSqlParameterSource()
                        .addValue("title", book.getTitle())
                        .addValue("author_id", book.getId())
                        .addValue("genre_id", book.getGenreId())
                        .addValue("published_at", book.getPublishedAt()),
                keyHolder,
                new String[]{"id"}
        );
        return keyHolder.getKey().longValue();
    }

    public void update(Book book) {
        namedParameterJdbcOperations.update(
                SQL_UPDATE,
                new MapSqlParameterSource()
                        .addValue("id", book.getId())
                        .addValue("title", book.getTitle())
                        .addValue("genre_id", book.getGenreId())
                        .addValue("published_at", book.getPublishedAt())
        );
    }

    public void deleteById(Long id) {
        namedParameterJdbcOperations.update(
                SQL_DELETE,
                new MapSqlParameterSource("id", id)
        );
    }
}