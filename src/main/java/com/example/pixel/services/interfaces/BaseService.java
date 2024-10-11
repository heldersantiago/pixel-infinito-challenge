package com.example.pixel.services.interfaces;

import com.example.pixel.entities.Author;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAll();

    T create(T entity);

    Optional<T> getById(Long id);

    T update(Long id, Map<String, Object> updates);

    void delete(Long id);
}
