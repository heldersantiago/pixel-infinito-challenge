package com.example.pixel.services;

import com.example.pixel.entities.Author;
import com.example.pixel.exceptions.ResourceNotFoundException;
import com.example.pixel.repositories.AuthorRepository;
import com.example.pixel.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author create(Author author) {
        if (Objects.equals(author.getName(), null)) {
            throw new RuntimeException("Author name is required");
        }
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> getById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return Optional.ofNullable(author);
    }

    @Override
    public Author update(Long id, Map<String, Object> updates) {
        Author existingAuthor = getById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        updates.forEach((key, value) -> {
            try {
                Field field = Author.class.getDeclaredField(key);
                field.setAccessible(true);
                if (value != null && field.getType().isAssignableFrom(value.getClass())) {
                    field.set(existingAuthor, value);
                }
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("No fields provided");

            } catch (IllegalAccessException ignored) {

            }
        });
        return authorRepository.save(existingAuthor);
    }

    @Override
    public void delete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        authorRepository.deleteById(id);
    }
}
