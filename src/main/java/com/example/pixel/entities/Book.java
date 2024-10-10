package com.example.pixel.entities;

import com.example.pixel.enums.BookGenre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date publishedDate;
    private String title;
    private String description;
    private BookGenre genre;

    @ManyToMany(mappedBy = "books")
    private final Set<Author> authors = new HashSet<>();
}
