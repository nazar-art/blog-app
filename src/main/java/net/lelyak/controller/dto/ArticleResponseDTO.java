package net.lelyak.controller.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Nazar Lelyak.
 */
public record ArticleResponseDTO(
        long id, String post,
        Instant createdAt, Instant updatedAt,
        long authorId, String authorName
) implements Serializable {
}
