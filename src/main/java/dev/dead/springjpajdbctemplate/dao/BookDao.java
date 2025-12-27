package dev.dead.springjpajdbctemplate.dao;


import dev.dead.springjpajdbctemplate.domain.Book;

import java.util.Optional;

public interface BookDao {
    Optional<Book> getById(Long id);

    Optional<Book> findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);
}