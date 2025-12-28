package dev.dead.springjpajdbctemplate.dao;

import dev.dead.springjpajdbctemplate.domain.Author;
import org.springframework.jdbc.core.ResultSetExtractor;

public class AuthorExtractor implements ResultSetExtractor<Author> {

    @Override
    public Author extractData(
            java.sql.ResultSet rs) throws java.sql.SQLException {
        rs.next();
        return new AuthorMapper().mapRow(rs, 0);

    }
}
