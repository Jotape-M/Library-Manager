package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.request.LivroRequestDTO;
import com.joaopedro.librarymanager.dto.response.LivroResponseDTO;
import com.joaopedro.librarymanager.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILivroService {

    Page<LivroResponseDTO> findAll(Pageable pageable);

    List<LivroResponseDTO> findAll();

    LivroResponseDTO create(LivroRequestDTO livroRequestDTO);

    LivroResponseDTO update(LivroRequestDTO livroRequestDTO);

    void deleteById(Long id);

    Livro verifyAndGetIfExists(Long livroId);
}
