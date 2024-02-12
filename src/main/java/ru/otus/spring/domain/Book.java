package ru.otus.spring.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "book")
@Entity(name = "Book")
@NamedEntityGraph(name = "book-author-genre-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("author"),
                @NamedAttributeNode("genre")
        }
)
public class Book extends AbstractJpaPersistable<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    /**
     * <a href="https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/">...</a>
     * The @ManyToOne annotation allows you to map the Foreign Key column in the child entity mapping
     * so that the child has an entity object reference to its parent entity. This is the most natural way of mapping
     * a database one-to-many database association, and, usually, the most efficient alternative too.
     * The best way to map a @OneToMany association is to rely on the @ManyToOne side to propagate all entity state changes:
     */
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Author author;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY)
    @JoinColumn
    private Genre genre;

    @OneToMany(
            targetEntity = Comment.class,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "book"
    )
    private List<Comment> comments;

    @Column(name = "published_at", nullable = false)
    private LocalDate publishedAt;
}
