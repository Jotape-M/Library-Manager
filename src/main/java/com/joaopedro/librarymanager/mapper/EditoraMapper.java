package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import com.joaopedro.librarymanager.model.Editora;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EditoraMapper {

    Editora toModel(EditoraDTO editoraDTO);

    EditoraDTO toDTO(Editora editora);
}
