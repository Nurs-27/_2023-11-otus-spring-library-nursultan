package ru.otus.spring.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.BookDetail;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDetailRowMapper implements RowMapper<BookDetail> {
    @Override
    public BookDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookDetail bookDetail = new BookDetail();
        bookDetail.setId(rs.getLong("id"));
        bookDetail.setTitle(rs.getString("title"));
        bookDetail.setPublishedAt(rs.getDate("publishedAt").toLocalDate());

        Author author = new Author();
        author.setId(rs.getLong("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setBirthDate(rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate());

        Genre genre = new Genre();
        genre.setId(rs.getLong("genreId"));
        genre.setName(rs.getString("genreName"));

        bookDetail.setAuthor(author);
        bookDetail.setGenre(genre);

        return bookDetail;
    }
}
