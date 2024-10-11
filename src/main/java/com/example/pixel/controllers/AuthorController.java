package com.example.pixel.controllers;

import com.example.pixel.dtos.AuthorDTO;
import com.example.pixel.dtos.PageResponseDTO;
import com.example.pixel.entities.Author;
import com.example.pixel.mappers.AuthorMapper;
import com.example.pixel.services.interfaces.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @Operation(summary = "Create an Author")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Author created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AbstractReadWriteAccess.Item.class))}), @ApiResponse(responseCode = "400", description = "Bad request"),})
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        // Convert DTO to entity
        Author author = authorMapper.toEntity(authorDTO);
        Author createdAuthor = authorService.create(author);
        // Convert back to DTO for response
        return ResponseEntity.status(HttpStatus.CREATED).body(authorMapper.toDto(createdAuthor));
    }

    @Operation(summary = "Get all authors")
    @ApiResponses(value = {@ApiResponse(responseCode = "200")})
    @GetMapping
    public ResponseEntity<PageResponseDTO<AuthorDTO>> getAuthors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        // Determine the sort direction
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // Create a pageable object with the page, size, and sort parameters
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Author> authorsPage = authorService.getAll(pageable);

        // Criando um DTO de resposta
        PageResponseDTO<AuthorDTO> response = new PageResponseDTO<>(
                authorsPage.getContent()
                        .stream()
                        .map(authorMapper::toDto)
                        .collect(Collectors.toList()),
                authorsPage.getNumber(),
                authorsPage.getSize(),
                authorsPage.getTotalElements(),
                authorsPage.getTotalPages()
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get an author by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Author Found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AbstractReadWriteAccess.Item.class))}), @ApiResponse(responseCode = "400", description = "Bad Request"), @ApiResponse(responseCode = "404", description = "Author not found")})
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.getById(id);
        return ResponseEntity.ok(authorMapper.toDto(author.orElse(null)));
    }

    @Operation(summary = "Delete an author")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Author deleted"), @ApiResponse(responseCode = "404", description = "Author not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update an author")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Author updated", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AbstractReadWriteAccess.Item.class))}), @ApiResponse(responseCode = "400", description = "Bad Request"), @ApiResponse(responseCode = "404", description = "Author not found")})
    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Author updatedAuthor = authorService.update(id, updates);
        return ResponseEntity.ok(authorMapper.toDto(updatedAuthor));
    }
}
