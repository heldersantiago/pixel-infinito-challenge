package com.example.pixel.dtos;

import java.util.Set;

public record AuthorDTO(
        Long id,
        String name,
        String biography,
        Set<Long> bookIds // Use book IDs for references
) {
}
