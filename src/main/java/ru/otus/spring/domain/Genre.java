package ru.otus.spring.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genre")
@Entity(name = "Genre")
public class Genre extends AbstractJpaPersistable<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(
            targetEntity = Book.class,
            cascade = { CascadeType.PERSIST },
            fetch = FetchType.LAZY,
            mappedBy = "genre"
    )
    private List<Book> books;
}
