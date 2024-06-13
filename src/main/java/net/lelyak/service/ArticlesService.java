package net.lelyak.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.lelyak.controller.dto.ArticleRequestDTO;
import net.lelyak.controller.dto.ArticleResponseDTO;
import net.lelyak.domain.Article;
import net.lelyak.domain.Author;
import net.lelyak.repository.ArticlesRepository;
import net.lelyak.repository.AuthorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @author Nazar Lelyak.
 */
@Service
@RequiredArgsConstructor
public class ArticlesService {

    private final ArticlesRepository articlesRepository;
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public Page<ArticleResponseDTO> getArticles(LocalDateTime date, Pageable pageable) {
        if (date != null) {
            return articlesRepository.findArticlesBefore(date, pageable);
        } else {
            return articlesRepository.getAllBy(ArticleResponseDTO.class, pageable);
        }
    }

    @Transactional(readOnly = true)
    public Article getArticle(Long id) {
        return articlesRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Article createArticle(ArticleRequestDTO dto) {
        Author author = getAuthor(dto.authorName());
        Article article = new Article(dto.post(), author);
        author.addArticle(article);
        return articlesRepository.save(article);
    }

    private Author getAuthor(String authorName) {
        Assert.notNull(authorName, "Author name must not be null");
        return authorRepository.findByName(authorName)
                .orElseGet(() -> authorRepository.save(new Author(authorName)));
    }

    @Transactional
    public Article updateArticle(Article fromDb, Article article) {
        fromDb.setPost(article.getPost());
        return articlesRepository.save(fromDb);
    }

    public void deleteArticle(Article article) {
        articlesRepository.delete(article);
    }
}
