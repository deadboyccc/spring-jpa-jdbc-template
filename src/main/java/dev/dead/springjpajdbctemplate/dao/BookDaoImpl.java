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
        Book result = jdbcTemplate.queryForObject("select * from book where title = ?", bookMapper, title);
        return Optional.ofNullable(result);
    }

    @Override
    public Book saveNewBook(Book book) {
        Long generatedId = jdbcTemplate.queryForObject(
                "INSERT INTO BOOK (TITLE, ISBN, PUBLISHER) VALUES (?, ?, ?) RETURNING ID",
                Long.class,
                book.getTitle(),
                book.getIsbn(),
                book.getPublisher()
        );
        book.setId(generatedId);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        jdbcTemplate.update("UPDATE BOOK SET TITLE = ?, ISBN = ?, PUBLISHER = ? WHERE ID = ?"
                , book.getTitle(), book.getIsbn(), book.getPublisher(), book.getId());
        return this.getById(book.getId())
                .orElse(null);
    }

    @Override
    public void deleteBookById(Long id) {
        jdbcTemplate.update("DELETE FROM BOOK WHERE ID = ?", id);


    }
}
