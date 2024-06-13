package net.lelyak.controller.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Nazar Lelyak.
 */
public record ArticleResponseDTO(long id, String post, LocalDateTime createdAt, LocalDateTime updatedAt, long authorId,
                                 String authorName) implements Serializable {
}
