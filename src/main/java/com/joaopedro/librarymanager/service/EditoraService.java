package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import com.joaopedro.librarymanager.exception.editora.EditoraCanNotBeDeletedException;
import com.joaopedro.librarymanager.exception.editora.EditoraNotFoundException;
import com.joaopedro.librarymanager.mapper.EditoraMapper;
import com.joaopedro.librarymanager.model.Editora;
import com.joaopedro.librarymanager.model.Livro;
import com.joaopedro.librarymanager.repository.EditoraRepository;
import com.joaopedro.librarymanager.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

    private final EditoraRepository editoraRepository;

    private final LivroRepository livroRepository;

    private final EditoraMapper editoraMapper;

    @Autowired
    public EditoraService(EditoraRepository editoraRepository, LivroRepository livroRepository, EditoraMapper editoraMapper) {
        this.editoraRepository = editoraRepository;
        this.livroRepository = livroRepository;
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
        for(Livro livro : livroRepository.findAll()) {
            if(id.equals(livro.getEditora().getId())) {
                throw new EditoraCanNotBeDeletedException(id, livro.getId());
            }
        }
        editoraRepository.deleteById(id);
    }

    public EditoraDTO update(EditoraDTO editoraDTO) {
        Editora editoraToUpdate = editoraMapper.toModel(editoraDTO);
        Editora editoraUpdated = editoraRepository.save(verifyAndGetIfExists(editoraToUpdate.getId()));

        return editoraMapper.toDTO(editoraUpdated);
    }

    public Editora verifyAndGetIfExists(Long id){
        return editoraRepository.findById(id).orElseThrow(() -> new EditoraNotFoundException(id));
    }
}
