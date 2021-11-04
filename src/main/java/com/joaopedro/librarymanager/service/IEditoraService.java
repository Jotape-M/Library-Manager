package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEditoraService {

    EditoraDTO create(EditoraDTO editoraDTO);

    Page<EditoraDTO> findAll(Pageable pageable);

    void deleteById(Long id);

    EditoraDTO update(EditoraDTO editoraDTO);
}
