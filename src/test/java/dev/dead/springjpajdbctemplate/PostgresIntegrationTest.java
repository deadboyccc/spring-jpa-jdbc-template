package dev.dead.springjpajdbctemplate;

import dev.dead.springjpajdbctemplate.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostgresIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(PostgresIntegrationTest.class);
    @Autowired
    BookRepository bookRepository;

    @Test
    void testSchemaMigration() {
        var count = bookRepository.count();
        log.debug("Found {} books", count);
        assert count > 0;
    }
}
