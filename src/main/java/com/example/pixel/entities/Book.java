package com.example.pixel.entities;

import com.example.pixel.enums.BookGenre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date publishedDate;
    @NotBlank(message = "book title is required")
    private String title;
    private String description;
    private BookGenre genre;

    @NotEmpty(message = "book must have at least one author")
    @ManyToMany(mappedBy = "books")
    private final Set<Author> authors = new HashSet<>();
}
