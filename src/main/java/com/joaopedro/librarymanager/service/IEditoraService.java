package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import com.joaopedro.librarymanager.model.Editora;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEditoraService {

    EditoraDTO create(EditoraDTO editoraDTO);

    Page<EditoraDTO> findAll(Pageable pageable);

    List<EditoraDTO> findAll();

    void deleteById(Long id);

    EditoraDTO update(EditoraDTO editoraDTO);

    Editora verifyAndGetIfExists(Long editoraId);
}
