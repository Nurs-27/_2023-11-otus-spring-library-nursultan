package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Adds join
    List<Comment> findByBookId(Long bookId);

    // Do not add join if object is already in Persistence Context
    List<Comment> findByBook(Book book);
}
