package net.lelyak.controller.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author Nazar Lelyak.
 */
public record ArticleRequestDTO(@NotEmpty String post, @NotEmpty String authorName) implements Serializable {
}
