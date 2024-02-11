package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-author-genre-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Book> findById(Long id);

    @EntityGraph(value = "book-author-genre-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAll();
}
