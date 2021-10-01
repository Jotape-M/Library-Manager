package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.request.AluguelRequestDTO;
import com.joaopedro.librarymanager.dto.response.AluguelResponseDTO;
import com.joaopedro.librarymanager.model.Aluguel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AluguelMapper {

    AluguelMapper INSTANCE = Mappers.getMapper(AluguelMapper.class);

    Aluguel toModel(AluguelRequestDTO aluguelRequestDTO);
    Aluguel toModel(AluguelResponseDTO aluguelRequestDTO);

    AluguelResponseDTO toDTO(Aluguel aluguel);
}
