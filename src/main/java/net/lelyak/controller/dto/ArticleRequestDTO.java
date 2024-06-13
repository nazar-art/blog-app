package net.lelyak.controller.dto;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * @author Nazar Lelyak.
 */
public record ArticleRequestDTO(@NotEmpty String post, @NotEmpty String authorName) implements Serializable {
}
