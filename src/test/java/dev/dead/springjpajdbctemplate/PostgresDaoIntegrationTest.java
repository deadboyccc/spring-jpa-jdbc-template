package dev.dead.springjpajdbctemplate;

import dev.dead.springjpajdbctemplate.dao.AuthorDao;
import dev.dead.springjpajdbctemplate.dao.AuthorDaoImpl;
import dev.dead.springjpajdbctemplate.dao.BookDao;
import dev.dead.springjpajdbctemplate.dao.BookDaoImpl;
import dev.dead.springjpajdbctemplate.domain.Author;
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
@Import({BookDaoImpl.class, AuthorDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostgresDaoIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(PostgresDaoIntegrationTest.class);

    @Autowired
    BookDao bookDao;

    @Autowired
    AuthorDao authorDao;

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

    // Author Tests

    @Test
    void testAuthorBeanRegistration() {
        assertNotNull(authorDao);
    }

    @Test
    void testGetAuthorById() {
        Author author = authorDao.getById(1L)
                .orElse(null);
        log.debug("Found author: {}", author);
        assertNotNull(author);
    }

    @Test
    void testCreateAuthor() {
        Author author = new Author("Test First", "Test Last");

        var returned = authorDao.saveNewAuthor(author);
        assertNotNull(returned);
        assertNotNull(returned.getId());
        assertEquals(author.getFirstName(), returned.getFirstName());
        assertEquals(author.getLastName(), returned.getLastName());

        log.debug("Created author: {}", returned);
    }

    @Test
    void testFindAuthorByName() {
        // First create an author with known name
        Author newAuthor = new Author("John", "Doe");
        var savedAuthor = authorDao.saveNewAuthor(newAuthor);
        assertNotNull(savedAuthor.getId());

        // Now find it by name
        Author author = authorDao.findAuthorByName("John", "Doe")
                .orElse(null);
        log.debug("Found author by name: {}", author);
        assertNotNull(author);
        assertEquals("John", author.getFirstName());
        assertEquals("Doe", author.getLastName());
    }

    @Test
    void testUpdateAuthor() {
        Author author = authorDao.getById(1L)
                .orElse(null);
        assertNotNull(author);
        author.setFirstName("Updated First");
        author.setLastName("Updated Last");

        var updatedAuthor = authorDao.updateAuthor(author);
        assertNotNull(updatedAuthor);
        assertEquals(author.getFirstName(), updatedAuthor.getFirstName());
        assertEquals(author.getLastName(), updatedAuthor.getLastName());
        log.debug("Updated author: {}", updatedAuthor);
    }

    @Test
    void testDeleteAuthorById() {
        // Create an author without any books referencing it
        Author newAuthor = new Author("Delete", "Me");
        var savedAuthor = authorDao.saveNewAuthor(newAuthor);
        Long authorId = savedAuthor.getId();
        assertNotNull(authorId);

        // Now delete it
        authorDao.deleteAuthorById(authorId);
        assertThrows(RuntimeException.class,
                () -> authorDao.getById(authorId)
                        .orElse(null));
    }

    // join table tests


    @Test
    void createBookAndsetAuthor() {
        Book book = new Book();
        book.setPublisher("Test Publisher");
        book.setTitle("Test Title");
        book.setIsbn("12341241");

        Author author = new Author("Test First", "Test Last");
        authorDao.saveNewAuthor(author);

        book.setAuthorId(author.getId());

        bookDao.saveNewBook(book);
        log.debug("Created book: {}", book);
        log.debug("Created author: {}", author);
        assertNotNull(book.getId());
        assertNotNull(book.getAuthorId());
        assertEquals(author.getId(), book.getAuthorId());

    }
}
