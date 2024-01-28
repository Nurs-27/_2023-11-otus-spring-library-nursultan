package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private AuthorDto authordto;
    private GenreDto genreDto;
    private LocalDate publishedAt;
}


