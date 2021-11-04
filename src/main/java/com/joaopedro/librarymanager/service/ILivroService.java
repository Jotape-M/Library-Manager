package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.request.LivroRequestDTO;
import com.joaopedro.librarymanager.dto.response.LivroResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILivroService {

    Page<LivroResponseDTO> findAll(Pageable pageable);

    LivroResponseDTO create(LivroRequestDTO livroRequestDTO);

    LivroResponseDTO update(LivroRequestDTO livroRequestDTO);

    void deleteById(Long id);
}
