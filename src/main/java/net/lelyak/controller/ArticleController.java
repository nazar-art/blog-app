package net.lelyak.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.lelyak.controller.dto.ArticleRequestDTO;
import net.lelyak.controller.dto.ArticleResponseDTO;
import net.lelyak.controller.mapper.ArticleMapper;
import net.lelyak.domain.Article;
import net.lelyak.service.ArticlesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * @author Nazar Lelyak.
 */
@RestController
@RequestMapping("/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticlesService articlesService;

    @GetMapping
    public Page<ArticleResponseDTO> list(
            @RequestParam(required = false)
            LocalDateTime date,
            @PageableDefault(size = 20, sort = "id")
            Pageable pageable
    ) {
        return articlesService.getArticles(date, pageable);
    }

    @GetMapping("{id}")
    public ArticleResponseDTO getOne(@PathVariable("id") Article article) {
        return ArticleMapper.toDto(article);
    }

    @PostMapping
    public ResponseEntity<ArticleResponseDTO> create(@Valid @RequestBody ArticleRequestDTO dto) {
        var saved = articlesService.createArticle(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(ArticleMapper.toDto(saved));
    }

    @PutMapping("{id}")
    public ResponseEntity<ArticleResponseDTO> update(
            @PathVariable("id") Article fromDb,
            @Valid @RequestBody Article article
    ) {
        var updated = articlesService.updateArticle(fromDb, article);
        return ResponseEntity.ok(ArticleMapper.toDto(updated));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Article article) {
        articlesService.deleteArticle(article);
    }
}
