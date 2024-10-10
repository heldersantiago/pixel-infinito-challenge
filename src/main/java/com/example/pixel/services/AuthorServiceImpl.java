package com.example.pixel.services;

import com.example.pixel.entities.Author;
import com.example.pixel.repositories.AuthorRepository;
import com.example.pixel.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return List.of();
    }

    @Override
    public void create(Author entity) {

    }

    @Override
    public Optional<Author> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Author update(Long id, Author entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
