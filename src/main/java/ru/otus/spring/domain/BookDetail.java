package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetail {

    private Long id;

    private String title;

    private Author author;

    private Genre genre;

    private LocalDate publishedAt;
}
