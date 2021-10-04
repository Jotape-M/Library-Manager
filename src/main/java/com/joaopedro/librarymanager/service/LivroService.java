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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroMapper livroMapper = LivroMapper.INSTANCE;

    private LivroRepository livroRepository;

    private EditoraService editoraService;

    @Autowired
    public LivroService(LivroRepository livroRepository, EditoraService editoraService) {
        this.livroRepository = livroRepository;
        this.editoraService = editoraService;
    }

    public Page<LivroResponseDTO> findAll(Pageable pageable) {
         List<LivroResponseDTO> livroResponseDTOList = livroRepository.findAll(pageable).stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());

         return new PageImpl<>(livroResponseDTOList);
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
        Livro livroUpdated = livroRepository.save(livroToUpdate);

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
