package dev.dead.springjpajdbctemplate;

import dev.dead.springjpajdbctemplate.dao.BookDao;
import dev.dead.springjpajdbctemplate.dao.BookDaoImpl;
import dev.dead.springjpajdbctemplate.domain.Book;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(BookDaoImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostgresDaoIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(PostgresDaoIntegrationTest.class);
    @Autowired
    BookDao bookDao;

    @Test
    void BeanRegisterationTest() {
        assertNotNull(bookDao);
    }

    @Test
    void testGetBookById() {
        Book book = bookDao.getById(1L)
                .orElse(null);
        log.debug("Found book: {}", book);
        assertNotNull(book);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setPublisher("Test Publisher");
        book.setTitle("Test Title");
        book.setIsbn("12341241");

        var returned = bookDao.saveNewBook(book);
        assertNotNull(returned);
        assertNotNull(returned.getId());
        assertEquals(book.getTitle(), returned.getTitle());
        assertEquals(book.getIsbn(), returned.getIsbn());
        assertEquals(book.getPublisher(), returned.getPublisher());

        log.debug("Created book: {}", returned);
    }

    @Test
    void testUpdateBookById() {
        Book book = bookDao.getById(1L)
                .orElse(null);
        assertNotNull(book);
        book.setTitle("Updated Title");
        var updatedBook = bookDao.updateBook(book);
        assertNotNull(updatedBook);
        assertEquals(book.getTitle(), updatedBook.getTitle());
    }

    @Test
    void testDeleteBookById() {
        bookDao.deleteBookById(1L);
        assertThrows(RuntimeException.class,
                () -> bookDao.getById(1L)
                        .orElse(null));
    }
}
