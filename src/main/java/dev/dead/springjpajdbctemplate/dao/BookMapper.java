package dev.dead.springjpajdbctemplate.dao;

import dev.dead.springjpajdbctemplate.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {
    public BookMapper() {
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublisher(rs.getString("publisher"));

        // Handle nullable author_id
        Long authorId = rs.getObject("author_id", Long.class);
        book.setAuthorId(authorId);

        return book;
    }
}
