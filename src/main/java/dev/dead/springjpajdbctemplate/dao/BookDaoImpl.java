package dev.dead.springjpajdbctemplate.dao;

import dev.dead.springjpajdbctemplate.domain.Book;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Import(BookMapper.class)
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;

    public BookDaoImpl(JdbcTemplate jdbcTemplate, BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }

    @Override
    public Optional<Book> getById(Long id) {
        Book result = jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE " +
                        "ID" +
                        " = ?",
                bookMapper, id);
        return Optional.ofNullable(result);
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
