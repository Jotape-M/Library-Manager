package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.UsuarioDTO;
import com.joaopedro.librarymanager.exception.usuario.UsuarioCanNotBeDeletedException;
import com.joaopedro.librarymanager.exception.usuario.UsuarioNotFoundException;
import com.joaopedro.librarymanager.mapper.UsuarioMapper;
import com.joaopedro.librarymanager.model.Aluguel;
import com.joaopedro.librarymanager.model.Usuario;
import com.joaopedro.librarymanager.repository.AluguelRepository;
import com.joaopedro.librarymanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioMapper usuarioMapper;

    private final UsuarioRepository usuarioRepository;

    private final AluguelRepository aluguelRepository;

    @Autowired
    public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, AluguelRepository aluguelRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.aluguelRepository = aluguelRepository;
    }

    public Page<UsuarioDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(usuarioMapper::toDTO);
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
        Usuario usuarioUpdated = usuarioRepository.save(verifyAndGetIfExists(usuarioToUpdate.getId()));

        return usuarioMapper.toDTO(usuarioUpdated);
    }

    public Usuario verifyAndGetIfExists(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }
}
