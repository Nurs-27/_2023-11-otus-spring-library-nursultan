package ru.otus.spring.mapper;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookDetail;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.dto.AuthorDto;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.dto.BookUpdatingRequest;
import ru.otus.spring.dto.GenreDto;

@Component
public class Mapper {

    public Book toBookEntity(BookDto dto, Long authorId, Long genreId) {
        Book entity = new Book();
        entity.setTitle(dto.getTitle());
        entity.setAuthorId(authorId);
        entity.setGenreId(genreId);
        entity.setPublishedAt(dto.getPublishedAt());
        return entity;
    }

    public Book toBookEntity(BookUpdatingRequest request) {
        if (request == null) {
            return null;
        }
        Book entity = new Book();
        entity.setTitle(request.getTitle());
        entity.setAuthorId(request.getAuthorId());
        entity.setGenreId(request.getGenreId());
        entity.setPublishedAt(request.getPublishedAt());
        return entity;
    }

    public BookDto toBookDto(BookDetail bookDetail) {
        if (bookDetail == null) {
            return null;
        }
        BookDto bookDto = new BookDto();
        bookDto.setId(bookDetail.getId());
        bookDto.setTitle(bookDetail.getTitle());

        AuthorDto authorDto = toAuthorDto(bookDetail.getAuthor());
        GenreDto genreDto = toGenreDto(bookDetail.getGenre());

        bookDto.setAuthorDto(authorDto);
        bookDto.setGenreDto(genreDto);

        return bookDto;
    }

    public Author toAuthorEntity(AuthorDto dto) {
        if (dto == null) {
            return null;
        }
        Author entity = new Author();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());

        return entity;
    }

    public AuthorDto toAuthorDto(Author entity) {
        if (entity == null) {
            return null;
        }
        AuthorDto dto = new AuthorDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setBirthDate(entity.getBirthDate());

        return dto;
    }

    public Genre toGenreEntity(GenreDto dto) {
        if (dto == null) {
            return null;
        }
        Genre entity = new Genre();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }

    public GenreDto toGenreDto(Genre entity) {
        if (entity == null) {
            return null;
        }
        GenreDto dto = new GenreDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }
}
