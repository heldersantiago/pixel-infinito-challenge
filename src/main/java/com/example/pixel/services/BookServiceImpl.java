package com.example.pixel.services;

import com.example.pixel.entities.Book;
import com.example.pixel.repositories.BookRepository;
import com.example.pixel.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return List.of();
    }

    @Override
    public void create(Book entity) {

    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Book update(Long id, Book entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
