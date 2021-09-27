package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.UsuarioDTO;
import com.joaopedro.librarymanager.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toModel(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);
}
