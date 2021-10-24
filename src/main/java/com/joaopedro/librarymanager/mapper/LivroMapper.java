package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.request.LivroRequestDTO;
import com.joaopedro.librarymanager.dto.response.LivroResponseDTO;
import com.joaopedro.librarymanager.model.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro toModel(LivroRequestDTO livroRequestDTO);
    Livro toModel(LivroResponseDTO livroRequestDTO);

    LivroResponseDTO toDTO(Livro livro);
}
