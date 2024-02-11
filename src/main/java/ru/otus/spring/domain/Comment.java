package ru.otus.spring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "comment")
@Entity(name = "Comment")
@NamedEntityGraph(name = "comment-book-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("book"),
        }
)
public class Comment extends AbstractJpaPersistable<Long> {

    @Column(name = "text", nullable = false)
    private String text;

    @JsonBackReference
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Book book;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "created_by", nullable = false)
    private String created_by;
}
