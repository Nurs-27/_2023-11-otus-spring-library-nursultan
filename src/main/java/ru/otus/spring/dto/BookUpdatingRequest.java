package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdatingRequest {
    private Long id;
    private String title;
    private Long authorId;
    private Long genreId;
    private LocalDate publishedAt;
}



