package com.joaopedro.librarymanager.service.impl;

import com.joaopedro.librarymanager.dto.request.LivroRequestDTO;
import com.joaopedro.librarymanager.dto.response.LivroResponseDTO;
import com.joaopedro.librarymanager.exception.livro.LivroCanNotBeDeletedException;
import com.joaopedro.librarymanager.exception.livro.LivroNotFoundException;
import com.joaopedro.librarymanager.mapper.LivroMapper;
import com.joaopedro.librarymanager.model.Aluguel;
import com.joaopedro.librarymanager.model.Editora;
import com.joaopedro.librarymanager.model.Livro;
import com.joaopedro.librarymanager.repository.AluguelRepository;
import com.joaopedro.librarymanager.repository.LivroRepository;
import com.joaopedro.librarymanager.service.IEditoraService;
import com.joaopedro.librarymanager.service.ILivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroServiceImpl implements ILivroService {

    private final LivroMapper livroMapper;

    private final LivroRepository livroRepository;

    private final IEditoraService editoraService;

    private final AluguelRepository aluguelRepository;

    @Autowired
    public LivroServiceImpl(LivroMapper livroMapper, LivroRepository livroRepository, EditoraServiceImpl editoraService, AluguelRepository aluguelRepository) {
        this.livroMapper = livroMapper;
        this.livroRepository = livroRepository;
        this.editoraService = editoraService;
        this.aluguelRepository = aluguelRepository;
    }

    public Page<LivroResponseDTO> findAll(Pageable pageable) {
         return livroRepository.findAll(pageable).map(livroMapper::toDTO);
    }

    public List<LivroResponseDTO> findAll() {
        return livroRepository.findAll().stream().map(livroMapper::toDTO).collect(Collectors.toList());
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
        for (Aluguel aluguel : aluguelRepository.findAll()) {
            if(id.equals(aluguel.getLivro().getId())) {
                throw new LivroCanNotBeDeletedException(id, aluguel.getId());
            }
        }
        livroRepository.deleteById(id);
    }

    public Livro verifyAndGetIfExists(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
    }
}
