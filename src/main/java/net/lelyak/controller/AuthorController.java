package net.lelyak.controller;

import lombok.RequiredArgsConstructor;
import net.lelyak.domain.Author;
import net.lelyak.repository.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nazar Lelyak.
 */
@RestController
@RequestMapping("/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;

    @GetMapping
    public List<Author> getAll() {
        return authorRepository.listAll();
    }

    @GetMapping("{name}")
    public Author getOne(@PathVariable String name) {
        return authorRepository.getAuthor(name);
    }
}
