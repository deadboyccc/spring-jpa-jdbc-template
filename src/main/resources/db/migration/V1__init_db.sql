-- Drop tables if they exist
-- CASCADE ensures that dependent objects (like the FK on book) are handled
DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS author CASCADE;

-- Create Author table first so Book can reference it
CREATE TABLE author
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);

-- Create Book table
CREATE TABLE book
(
    id         BIGSERIAL PRIMARY KEY,
    isbn       VARCHAR(255),
    publisher  VARCHAR(255),
    title      VARCHAR(255),
    author_id  BIGINT,
    CONSTRAINT book_author_fk FOREIGN KEY (author_id) REFERENCES author (id)
);

-- Insert Data
INSERT INTO author (first_name, last_name) VALUES ('Craig', 'Walls');

INSERT INTO book (isbn, publisher, title, author_id)
VALUES ('978-1617294945', 'Simon & Schuster', 'Spring in Action, 5th Edition',
        (SELECT id FROM author WHERE first_name = 'Craig' AND last_name = 'Walls'));

INSERT INTO book (isbn, publisher, title, author_id)
VALUES ('978-1617292545', 'Simon & Schuster', 'Spring Boot in Action, 1st Edition',
        (SELECT id FROM author WHERE first_name = 'Craig' AND last_name = 'Walls'));

INSERT INTO book (isbn, publisher, title, author_id)
VALUES ('978-1617297571', 'Simon & Schuster', 'Spring in Action, 6th Edition',
        (SELECT id FROM author WHERE first_name = 'Craig' AND last_name = 'Walls'));

INSERT INTO author (first_name, last_name) VALUES ('Eric', 'Evans');

INSERT INTO book (isbn, publisher, title, author_id)
VALUES ('978-0321125217', 'Addison Wesley', 'Domain-Driven Design',
        (SELECT id FROM author WHERE first_name = 'Eric' AND last_name = 'Evans'));

INSERT INTO author (first_name, last_name) VALUES ('Robert', 'Martin');

INSERT INTO book (isbn, publisher, title, author_id)
VALUES ('978-0134494166', 'Addison Wesley', 'Clean Code',
        (SELECT id FROM author WHERE first_name = 'Robert' AND last_name = 'Martin'));