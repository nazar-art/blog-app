package net.lelyak.repository;

import net.lelyak.domain.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @EntityGraph(attributePaths = "articles")
    Optional<Author> findByName(String name);
}
