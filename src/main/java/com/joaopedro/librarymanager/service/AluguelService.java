package com.joaopedro.librarymanager.service;

import com.joaopedro.librarymanager.dto.request.AluguelRequestDTO;
import com.joaopedro.librarymanager.dto.response.AluguelResponseDTO;
import com.joaopedro.librarymanager.mapper.AluguelMapper;
import com.joaopedro.librarymanager.model.Aluguel;
import com.joaopedro.librarymanager.model.Livro;
import com.joaopedro.librarymanager.model.Usuario;
import com.joaopedro.librarymanager.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AluguelService {

    private final AluguelMapper aluguelMapper = AluguelMapper.INSTANCE;

    private AluguelRepository aluguelRepository;

    private LivroService livroService;

    private  UsuarioService usuarioService;

    @Autowired
    public AluguelService(AluguelRepository aluguelRepository, LivroService livroService, UsuarioService usuarioService) {
        this.aluguelRepository = aluguelRepository;
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    public List<AluguelResponseDTO> findAll() {
        return aluguelRepository.findAll()
                .stream()
                .map(aluguelMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AluguelResponseDTO create(AluguelRequestDTO aluguelRequestDTO) {
        Livro foundLivro = livroService.verifyAndGetIfExists(aluguelRequestDTO.getLivroId());
        Usuario foundUsuario = usuarioService.verifyAndGetIfExists(aluguelRequestDTO.getUsuarioId());

        Aluguel aluguelToCreate = aluguelMapper.toModel(aluguelRequestDTO);
        aluguelToCreate.setLivro(foundLivro);
        aluguelToCreate.setUsuario(foundUsuario);
        Aluguel aluguelCreated = aluguelRepository.save(aluguelToCreate);

        return aluguelMapper.toDTO(aluguelCreated);
    }

    public AluguelResponseDTO update(AluguelRequestDTO aluguelRequestDTO) {
        Livro foundLivro = livroService.verifyAndGetIfExists(aluguelRequestDTO.getLivroId());
        Usuario foundUsuario = usuarioService.verifyAndGetIfExists(aluguelRequestDTO.getUsuarioId());

        Aluguel aluguelToUpdate = aluguelMapper.toModel(aluguelRequestDTO);
        aluguelToUpdate.setLivro(foundLivro);
        aluguelToUpdate.setUsuario(foundUsuario);
        Aluguel aluguelUpdated = aluguelRepository.save(aluguelToUpdate);

        return aluguelMapper.toDTO(aluguelUpdated);
    }

    public void deleteById(Long id) {
        aluguelRepository.deleteById(id);
    }
}