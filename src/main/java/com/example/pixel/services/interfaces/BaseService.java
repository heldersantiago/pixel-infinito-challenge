package com.example.pixel.services.interfaces;

import com.example.pixel.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BaseService<T> {
    Page<T> getAll(Pageable pageable);

    T create(T entity);

    Optional<T> getById(Long id);

    T update(Long id, Map<String, Object> updates);

    void delete(Long id);
}
