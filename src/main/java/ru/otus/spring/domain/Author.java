package ru.otus.spring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
@Entity(name = "Author")
public class Author extends AbstractJpaPersistable<Long> {

    private String firstName;

    private String lastName;

    private LocalDateTime birthDate;

    @OneToMany(
            targetEntity = Book.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "author"
    )
    private List<Book> books;
}
