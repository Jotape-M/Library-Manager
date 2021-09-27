package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import com.joaopedro.librarymanager.model.Editora;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EditoraMapper {

    EditoraMapper INSTANCE = Mappers.getMapper(EditoraMapper.class);

    Editora toModel(EditoraDTO editoraDTO);

    EditoraDTO toDTO(Editora editora);
}
