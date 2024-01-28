package ru.otus.spring.mapper;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.GenreDto;

@Component
public class Mapper {

    public Book toBookEntity(BookDto dto) {
        Book entity = new Book();
        entity.setTitle(dto.getTitle());
        entity.setAuthor(toAuthorEntity(dto.getAuthordto()));
        entity.setGenre(toGenreEntity(dto.getGenreDto()));
        entity.setPublishedAt(dto.getPublishedAt());
        return entity;
    }

    public BookDto toBookDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthordto(toAuthorDto(book.getAuthor()));
        dto.setGenreDto(toGenreDto(book.getGenre()));
        dto.setPublishedAt(book.getPublishedAt());
        return dto;
    }

    public Author toAuthorEntity(AuthorDto dto) {
        if (dto == null) {
            return null;
        }
        Author entity = new Author();
        dto.setId(dto.getId());
        dto.setFirstName(dto.getFirstName());
        dto.setLastName(dto.getLastName());
        dto.setBirthDate(dto.getBirthDate());
        return entity;
    }


    public AuthorDto toAuthorDto(Author author) {
        if (author == null) {
            return null;
        }
        AuthorDto dto = new AuthorDto();
        dto.setId(author.getId());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setBirthDate(author.getBirthDate());
        return dto;
    }


    public Genre toGenreEntity(GenreDto dto) {
        if (dto == null) {
            return null;
        }
        Genre entity = new Genre();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return entity;
    }

    public GenreDto toGenreDto(Genre genre) {
        if (genre == null) {
            return null;
        }
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }
}
