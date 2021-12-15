package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.request.AluguelRequestDTO;
import com.joaopedro.librarymanager.dto.response.AluguelResponseDTO;
import com.joaopedro.librarymanager.model.Aluguel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAluguelService {

    Page<AluguelResponseDTO> findAll(Pageable pageable);

    List<AluguelResponseDTO> findAll();

    AluguelResponseDTO create(AluguelRequestDTO aluguelRequestDTO);

    AluguelResponseDTO update(AluguelRequestDTO aluguelRequestDTO);

    void deleteById(Long id);

    Aluguel verifyAndGetIfExists(Long id);
}
