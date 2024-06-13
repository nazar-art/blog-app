package net.lelyak.repository;

import net.lelyak.controller.dto.ArticleResponseDTO;
import net.lelyak.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

/**
 * @author Nazar Lelyak.
 */
public interface ArticlesRepository extends JpaRepository<Article, Long> {

    @Query("select new net.lelyak.controller.dto.ArticleResponseDTO(a.id, a.post, a.createdAt, a.updatedAt, a.author.id, a.author.name) " +
            "from Article a " +
            "where a.createdAt < :date")
    Page<ArticleResponseDTO> findArticlesBefore(@Param("date") LocalDateTime date, Pageable pageable);

    <T> Page<T> getAllBy(Class<T> type, Pageable pageable);
}
