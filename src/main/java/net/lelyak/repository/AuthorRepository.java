package net.lelyak.repository;

import net.lelyak.domain.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @EntityGraph(attributePaths = "articles")
    Optional<Author> findByName(String name);

    @Query("from Author a left join fetch a.articles")
    List<Author> listAll();

    @Query("from Author a left join fetch a.articles where a.name = :name")
    Author getAuthor(@Param("name") String name);
}
