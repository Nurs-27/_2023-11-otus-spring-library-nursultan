--liquibase formatted sql

--changeset nursd:1
CREATE TABLE author
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    birth_date TIMESTAMP
);

CREATE TABLE genre
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE book
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) UNIQUE NOT NULL,
    published_at DATE                NOT NULL,
    author_id    BIGINT,
    genre_id     BIGINT,
    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (genre_id) REFERENCES genre (id)
);

CREATE INDEX idx_book_author ON book (author_id);
CREATE INDEX idx_book_genre ON book (genre_id);

CREATE TABLE comment
(
    id         BIGSERIAL PRIMARY KEY,
    text       TEXT                    NOT NULL,
    book_id    BIGINT                  NOT NULL,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    created_by VARCHAR                 NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE INDEX idx_comment_book ON comment (book_id);

--changeset nursd:2
INSERT INTO author (first_name, last_name, birth_date)
VALUES ('J.D.', 'Salinger', '1919-01-01'),
       ('Jane', 'Austen', '1775-12-16'),
       ('Margaret', 'Mitchell', '1900-11-08'),
       ('Stephen', 'Covey', '1932-10-24'),
       ('Yuval Noah', 'Harari', '1976-02-24');


INSERT INTO genre (name)
VALUES ('Literature'),
       ('Self-help'),
       ('Historical'),
       ('Non-fiction');

INSERT INTO book (title, published_at, author_id, genre_id)
VALUES ('The Catcher in the Rye', '1951-07-16', 1, 1),
       ('Pride and Prejudice', '1813-01-28', 2, 1),
       ('Gone with the Wind', '1936-06-30', 3, 3),
       ('The 7 Habits of Highly Effective People', '1989-08-15', 4, 2),
       ('Sapiens: A Brief History of Humankind', '2011-01-01', 5, 4);

INSERT INTO comment (text, book_id, created_by)
VALUES ('That is my favorite book', 1, 'user1'),
       ('That favorite book of Bill Gates as well', 1, 'user2'),
       ('The main character is a very thinking young boy', 1, 'user3')

