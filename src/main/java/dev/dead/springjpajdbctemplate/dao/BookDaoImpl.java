package dev.dead.springjpajdbctemplate.dao;

import dev.dead.springjpajdbctemplate.domain.Book;

import java.util.Optional;

public class BookDaoImpl implements BookDao {
    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Book saveNewBook(Book book) {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }
}
