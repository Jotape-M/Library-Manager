package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import com.joaopedro.librarymanager.exception.EditoraNotFoundException;
import com.joaopedro.librarymanager.mapper.EditoraMapper;
import com.joaopedro.librarymanager.model.Editora;
import com.joaopedro.librarymanager.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditoraService {

    private final EditoraMapper editoraMapper = EditoraMapper.INSTANCE;

    @Autowired
    private EditoraRepository editoraRepository;

    public EditoraDTO create(EditoraDTO editoraDTO) {
        Editora editoraToCreate = editoraMapper.toModel(editoraDTO);
        Editora editoraCreated = editoraRepository.save(editoraToCreate);

        return editoraMapper.toDTO(editoraCreated);
    }

    public Page<EditoraDTO> findAll(Pageable pageable) {
        List<EditoraDTO> editoraDTOList = editoraRepository.findAll(pageable).stream()
                .map(editoraMapper::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(editoraDTOList);
    }

    public void deleteById(Long id) {
        editoraRepository.deleteById(id);
    }

    public EditoraDTO update(EditoraDTO editoraDTO) {
        Editora editoraToUpdate = editoraMapper.toModel(editoraDTO);
        Editora editoraUpdated = editoraRepository.save(editoraToUpdate);

        return  editoraMapper.toDTO(editoraUpdated);
    }

    public Editora verifyAndGetIfExists(Long id){
        return editoraRepository.findById(id)
                .orElseThrow(() -> new EditoraNotFoundException(id));
    }
}
