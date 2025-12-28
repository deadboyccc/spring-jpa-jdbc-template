package dev.dead.springjpajdbctemplate.dao;

import dev.dead.springjpajdbctemplate.domain.Author;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Import(AuthorMapper.class)
public class AuthorDaoImpl implements AuthorDao {
    private final JdbcTemplate jdbcTemplate;
    private final AuthorMapper authorMapper;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate, AuthorMapper authorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.authorMapper = authorMapper;
    }

    @Override
    public Optional<Author> getById(Long id) {
        Author result = jdbcTemplate.queryForObject(
                "SELECT * FROM AUTHOR WHERE ID = ?",
                authorMapper,
                id
        );
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Author> findAuthorByName(String firstName,
                                             String lastName) {
        Author result = jdbcTemplate.queryForObject(
                "SELECT * FROM AUTHOR WHERE FIRST_NAME = ? AND LAST_NAME = ?",
                authorMapper,
                firstName,
                lastName
        );
        return Optional.ofNullable(result);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        Long generatedId = jdbcTemplate.queryForObject(
                "INSERT INTO AUTHOR (FIRST_NAME, LAST_NAME) VALUES (?, ?) RETURNING ID",
                Long.class,
                author.getFirstName(),
                author.getLastName()
        );
        author.setId(generatedId);
        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update(
                "UPDATE AUTHOR SET FIRST_NAME = ?, LAST_NAME = ? WHERE ID = ?",
                author.getFirstName(),
                author.getLastName(),
                author.getId()
        );
        return this.getById(author.getId())
                .orElse(null);
    }

    @Override
    public void deleteAuthorById(Long id) {
        jdbcTemplate.update("DELETE FROM AUTHOR WHERE ID = ?", id);
    }
}
