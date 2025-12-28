package dev.dead.springjpajdbctemplate.dao;

import dev.dead.springjpajdbctemplate.domain.Author;

import java.util.Optional;

public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Optional<Author> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> findAuthorByName(String firstName,
                                             String lastName) {
        return Optional.empty();
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }
}
