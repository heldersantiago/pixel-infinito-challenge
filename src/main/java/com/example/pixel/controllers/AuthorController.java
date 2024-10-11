package com.example.pixel.controllers;

import com.example.pixel.dtos.AuthorDTO;
import com.example.pixel.entities.Author;
import com.example.pixel.mappers.AuthorMapper;
import com.example.pixel.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        // Convert DTO to entity
        Author author = authorMapper.toEntity(authorDTO);
        Author createdAuthor = authorService.create(author);
        // Convert back to DTO for response
        return ResponseEntity.status(HttpStatus.CREATED).body(authorMapper.toDto(createdAuthor));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        List<Author> authors = authorService.getAll();
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(authorMapper::toDto)
                .toList();
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getById(id);
        return ResponseEntity.ok(authorMapper.toDto(author.orElse(null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Author updatedAuthor = authorService.update(id, updates);
        return ResponseEntity.ok(authorMapper.toDto(updatedAuthor));
    }
}
