package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.request.AluguelRequestDTO;
import com.joaopedro.librarymanager.dto.response.AluguelResponseDTO;
import com.joaopedro.librarymanager.model.Aluguel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AluguelMapper {

    Aluguel toModel(AluguelRequestDTO aluguelRequestDTO);
    Aluguel toModel(AluguelResponseDTO aluguelRequestDTO);

    AluguelResponseDTO toDTO(Aluguel aluguel);
}
