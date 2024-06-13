package net.lelyak.repository;

import net.lelyak.controller.dto.ArticleResponseDTO;
import net.lelyak.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Nazar Lelyak.
 */
public interface ArticlesRepository extends JpaRepository<Article, Long> {

    @Query("select new net.lelyak.controller.dto.ArticleResponseDTO(a.id, a.post, a.createdAt, a.updatedAt, a.author.id, a.author.name) " +
            "from Article a " +
            "where a.createdAt < :date")
    Page<ArticleResponseDTO> findArticlesBefore(@Param("date") LocalDateTime date, Pageable pageable);

    <T> Page<T> getAllBy(Class<T> type, Pageable pageable);

    @Query("from Article a where a.createdAt < :date")
    Page<Article> findAllCreatedBefore(@Param("date") LocalDateTime date, Pageable pageable);

    Page<Article> findByCreatedAtLessThan(LocalDateTime date, Pageable pageable);

    <T> Optional<T> getOneBy(Class<T> type);

}
