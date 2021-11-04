package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.request.LivroRequestDTO;
import com.joaopedro.librarymanager.dto.response.LivroResponseDTO;
import com.joaopedro.librarymanager.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    Livro toModel(LivroRequestDTO livroRequestDTO);
    Livro toModel(LivroResponseDTO livroRequestDTO);

    LivroResponseDTO toDTO(Livro livro);

    @Mapping(target = "editoraId", source = "editora.id")
    LivroRequestDTO toRequestDTO(Livro livro);
}
