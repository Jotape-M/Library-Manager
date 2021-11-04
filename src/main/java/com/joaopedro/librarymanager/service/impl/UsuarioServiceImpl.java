package com.joaopedro.librarymanager.service.impl;

import com.joaopedro.librarymanager.dto.UsuarioDTO;
import com.joaopedro.librarymanager.exception.usuario.UsuarioCanNotBeDeletedException;
import com.joaopedro.librarymanager.exception.usuario.UsuarioNotFoundException;
import com.joaopedro.librarymanager.mapper.UsuarioMapper;
import com.joaopedro.librarymanager.model.Aluguel;
import com.joaopedro.librarymanager.model.Usuario;
import com.joaopedro.librarymanager.repository.AluguelRepository;
import com.joaopedro.librarymanager.repository.UsuarioRepository;
import com.joaopedro.librarymanager.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioMapper usuarioMapper;

    private final UsuarioRepository usuarioRepository;

    private final AluguelRepository aluguelRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, AluguelRepository aluguelRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.aluguelRepository = aluguelRepository;
    }

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(usuarioMapper::toDTO);
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDTO).collect(Collectors.toList());
    }

    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        Usuario usuarioToCreate = usuarioMapper.toModel(usuarioDTO);
        Usuario usuarioCreated = usuarioRepository.save(usuarioToCreate);

        return usuarioMapper.toDTO(usuarioCreated);
    }

    public void delete(Long id) {
        for(Aluguel aluguel : aluguelRepository.findAll()) {
            if(id.equals(aluguel.getUsuario().getId())) {
                throw new UsuarioCanNotBeDeletedException(id, aluguel.getId());
            }
        }
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
