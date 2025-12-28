package dev.dead.springjpajdbctemplate.dao;

import dev.dead.springjpajdbctemplate.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> getById(Long id);

    Optional<Author> findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
