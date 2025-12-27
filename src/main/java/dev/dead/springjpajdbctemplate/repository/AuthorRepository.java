package dev.dead.springjpajdbctemplate.repository;


import dev.dead.springjpajdbctemplate.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}