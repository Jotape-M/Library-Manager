package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.request.AluguelRequestDTO;
import com.joaopedro.librarymanager.dto.response.AluguelResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAluguelService {

    Page<AluguelResponseDTO> findAll(Pageable pageable);

    AluguelResponseDTO create(AluguelRequestDTO aluguelRequestDTO);

    AluguelResponseDTO update(AluguelRequestDTO aluguelRequestDTO);

    void deleteById(Long id);
}
