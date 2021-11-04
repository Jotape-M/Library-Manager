package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService {

    Page<UsuarioDTO> findAll(Pageable pageable);

    UsuarioDTO create(UsuarioDTO usuarioDTO);

    void delete(Long id);

    UsuarioDTO update(UsuarioDTO usuarioDTO);
}
