package com.example.pixel.mappers;

import com.example.pixel.dtos.AuthorDTO;
import com.example.pixel.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO toDto(Author author);

    Author toEntity(AuthorDTO authorDTO);
}
