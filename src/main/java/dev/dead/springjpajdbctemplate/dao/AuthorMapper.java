package dev.dead.springjpajdbctemplate.dao;

import dev.dead.springjpajdbctemplate.domain.Author;
import dev.dead.springjpajdbctemplate.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthorMapper implements RowMapper<Author> {
    public AuthorMapper() {
    }

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        try {
            if (rs.getString("isbn") != null) {
                author.getBooks()
                        .add(new Book(
                                rs.getString("title"),
                                rs.getString("isbn"),
                                rs.getString("publisher")));
                while (rs.next()) {
                    author.getBooks()
                            .add(new Book(
                                    rs.getString("title"),
                                    rs.getString("isbn"),
                                    rs.getString("publisher")));
                }
            }
        } catch (SQLException sql) {
            // Ignore if book columns don't exist
        }
        return author;
    }
}
