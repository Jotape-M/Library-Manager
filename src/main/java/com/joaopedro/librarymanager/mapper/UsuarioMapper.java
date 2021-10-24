package com.joaopedro.librarymanager.mapper;

import com.joaopedro.librarymanager.dto.UsuarioDTO;
import com.joaopedro.librarymanager.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toModel(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(Usuario usuario);
}
