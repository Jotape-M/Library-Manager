package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.UsuarioDTO;
import com.joaopedro.librarymanager.exception.UsuarioNotFoundException;
import com.joaopedro.librarymanager.mapper.UsuarioMapper;
import com.joaopedro.librarymanager.model.Usuario;
import com.joaopedro.librarymanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioMapper usuarioMapper = UsuarioMapper.INSTANCE;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        List<UsuarioDTO> usuarioDTOList = usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(usuarioDTOList);
    }

    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        Usuario usuarioToCreate = usuarioMapper.toModel(usuarioDTO);
        Usuario usuarioCreated = usuarioRepository.save(usuarioToCreate);

        return usuarioMapper.toDTO(usuarioCreated);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO update(UsuarioDTO usuarioDTO) {
        Usuario usuarioToUpdate = usuarioMapper.toModel(usuarioDTO);
        Usuario usuarioUpdated = usuarioRepository.save(usuarioToUpdate);

        return usuarioMapper.toDTO(usuarioUpdated);
    }

    public Usuario verifyAndGetIfExists(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }
}
