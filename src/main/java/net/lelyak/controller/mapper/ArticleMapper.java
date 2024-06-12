package net.lelyak.controller.mapper;

import net.lelyak.controller.dto.ArticleResponseDTO;
import net.lelyak.domain.Article;

/**
 * @author Nazar Lelyak.
 */
public final class ArticleMapper {
    public static ArticleResponseDTO toDto(Article article) {
        return new ArticleResponseDTO(article.getId(), article.getPost(), article.getCreatedAt(), article.getAuthor().getId(), article.getAuthor().getName());
    }
}
