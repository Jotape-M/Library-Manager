package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import com.joaopedro.librarymanager.exception.EditoraNotFoundException;
import com.joaopedro.librarymanager.mapper.EditoraMapper;
import com.joaopedro.librarymanager.model.Editora;
import com.joaopedro.librarymanager.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

    private final EditoraRepository editoraRepository;

    private final EditoraMapper editoraMapper;

    @Autowired
    public EditoraService(EditoraRepository editoraRepository, EditoraMapper editoraMapper) {
        this.editoraRepository = editoraRepository;
        this.editoraMapper = editoraMapper;
    }

    public EditoraDTO create(EditoraDTO editoraDTO) {
        Editora editoraToCreate = editoraMapper.toModel(editoraDTO);
        Editora editoraCreated = editoraRepository.save(editoraToCreate);

        return editoraMapper.toDTO(editoraCreated);
    }

    public Page<EditoraDTO> findAll(Pageable pageable) {
        return editoraRepository.findAll(pageable).map(editoraMapper::toDTO);
    }

    public void deleteById(Long id) {
        editoraRepository.deleteById(id);
    }

    public EditoraDTO update(EditoraDTO editoraDTO) {
        Editora editoraToUpdate = editoraMapper.toModel(editoraDTO);
        Editora editoraUpdated = editoraRepository.save(editoraToUpdate);

        return editoraMapper.toDTO(editoraUpdated);
    }

    public Editora verifyAndGetIfExists(Long id){
        return editoraRepository.findById(id)
                .orElseThrow(() -> new EditoraNotFoundException(id));
    }
}
