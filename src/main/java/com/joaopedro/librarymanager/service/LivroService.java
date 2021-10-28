package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.request.LivroRequestDTO;
import com.joaopedro.librarymanager.dto.response.LivroResponseDTO;
import com.joaopedro.librarymanager.exception.LivroNotFoundException;
import com.joaopedro.librarymanager.mapper.LivroMapper;
import com.joaopedro.librarymanager.model.Editora;
import com.joaopedro.librarymanager.model.Livro;
import com.joaopedro.librarymanager.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroMapper livroMapper;

    private final LivroRepository livroRepository;

    private final EditoraService editoraService;

    @Autowired
    public LivroService(LivroMapper livroMapper, LivroRepository livroRepository, EditoraService editoraService) {
        this.livroMapper = livroMapper;
        this.livroRepository = livroRepository;
        this.editoraService = editoraService;
    }

    public Page<LivroResponseDTO> findAll(Pageable pageable) {
         return livroRepository.findAll(pageable).map(livroMapper::toDTO);
    }

    public LivroResponseDTO create(LivroRequestDTO livroRequestDTO) {
        Editora foundEditora = editoraService.verifyAndGetIfExists(livroRequestDTO.getEditoraId());

        Livro livroToCreate = livroMapper.toModel(livroRequestDTO);
        livroToCreate.setEditora(foundEditora);
        Livro livroCreated = livroRepository.save(livroToCreate);

        return livroMapper.toDTO(livroCreated);
    }

    public LivroResponseDTO update(LivroRequestDTO livroRequestDTO) {
        Editora foundEditora = editoraService.verifyAndGetIfExists(livroRequestDTO.getEditoraId());

        Livro livroToUpdate = livroMapper.toModel(livroRequestDTO);
        livroToUpdate.setEditora(foundEditora);
        Livro livroUpdated = livroRepository.save(verifyAndGetIfExists(livroToUpdate.getId()));

        return livroMapper.toDTO(livroUpdated);
    }

    public void deleteById(Long id) {
        livroRepository.deleteById(id);
    }

    public Livro verifyAndGetIfExists(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
    }
}
