package dev.dead.springjpajdbctemplate.repository;

import dev.dead.springjpajdbctemplate.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}