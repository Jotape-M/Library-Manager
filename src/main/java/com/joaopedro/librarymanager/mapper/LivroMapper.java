package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.LivroDTO;
import com.joaopedro.librarymanager.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LivroMapper {

    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    Livro toModel(LivroDTO livroDTO);

    LivroDTO toDTO(Livro livro);
}
