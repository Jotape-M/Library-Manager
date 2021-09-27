package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.LivroDTO;
import com.joaopedro.librarymanager.mapper.LivroMapper;
import com.joaopedro.librarymanager.model.Livro;
import com.joaopedro.librarymanager.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroMapper livroMapper = LivroMapper.INSTANCE;

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> findAll() {
         return livroRepository.findAll().stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LivroDTO create(LivroDTO livroDTO) {
        Livro livroToCreate = livroMapper.toModel(livroDTO);
        Livro livroCreated = livroRepository.save(livroToCreate);

        return livroMapper.toDTO(livroCreated);
    }

    public LivroDTO update(LivroDTO livroDTO) {
        Livro livroToUpdate = livroMapper.toModel(livroDTO);
        Livro livroUpdated = livroRepository.save(livroToUpdate);

        return livroMapper.toDTO(livroUpdated);
    }

    public void deleteById(Long id) {
        livroRepository.deleteById(id);
    }
}
