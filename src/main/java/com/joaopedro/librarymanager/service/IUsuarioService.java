package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuarioService {

    Page<UsuarioDTO> findAll(Pageable pageable);

    List<UsuarioDTO> findAll();

    UsuarioDTO create(UsuarioDTO usuarioDTO);

    void delete(Long id);

    UsuarioDTO update(UsuarioDTO usuarioDTO);
}
