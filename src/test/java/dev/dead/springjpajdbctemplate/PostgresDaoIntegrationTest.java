package dev.dead.springjpajdbctemplate;

import dev.dead.springjpajdbctemplate.dao.BookDao;
import dev.dead.springjpajdbctemplate.dao.BookDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostgresDaoIntegrationTest {
    @Autowired
    BookDao bookDao;

    @Test
    void name() {
        assertNotNull(bookDao);
    }
}
