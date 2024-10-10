package com.example.pixel.services.interfaces;

import com.example.pixel.entities.Author;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAll();

    void create(T entity);

    Optional<T> getById(Long id);

    T update(Long id, T entity);

    void delete(Long id);
}
