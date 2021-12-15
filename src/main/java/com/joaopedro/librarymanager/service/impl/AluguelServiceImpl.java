package com.joaopedro.librarymanager.service.impl;

import com.joaopedro.librarymanager.dto.request.AluguelRequestDTO;
import com.joaopedro.librarymanager.dto.response.AluguelResponseDTO;
import com.joaopedro.librarymanager.exception.aluguel.AluguelDataNotValidException;
import com.joaopedro.librarymanager.exception.aluguel.AluguelNotFoundException;
import com.joaopedro.librarymanager.exception.livro.LivroInsufficientQuantity;
import com.joaopedro.librarymanager.mapper.AluguelMapper;
import com.joaopedro.librarymanager.mapper.LivroMapper;
import com.joaopedro.librarymanager.model.Aluguel;
import com.joaopedro.librarymanager.model.Livro;
import com.joaopedro.librarymanager.model.Usuario;
import com.joaopedro.librarymanager.repository.AluguelRepository;
import com.joaopedro.librarymanager.service.IAluguelService;
import com.joaopedro.librarymanager.service.ILivroService;
import com.joaopedro.librarymanager.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AluguelServiceImpl implements IAluguelService {

    private final AluguelMapper aluguelMapper;

    private final AluguelRepository aluguelRepository;

    private final ILivroService livroService;

    private final LivroMapper livroMapper;

    private final IUsuarioService usuarioService;

    @Autowired
    public AluguelServiceImpl(AluguelMapper aluguelMapper, AluguelRepository aluguelRepository,
                              LivroServiceImpl livroService, LivroMapper livroMapper, UsuarioServiceImpl usuarioService) {
        this.aluguelMapper = aluguelMapper;
        this.aluguelRepository = aluguelRepository;
        this.livroService = livroService;
        this.livroMapper = livroMapper;
        this.usuarioService = usuarioService;
    }

    public Page<AluguelResponseDTO> findAll(Pageable pageable) {
        return aluguelRepository.findAll(pageable).map(aluguelMapper::toDTO);
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

        if(aluguelRequestDTO.getDataAluguel().isAfter(aluguelRequestDTO.getDataPrevisao())) {
            throw new AluguelDataNotValidException("Data de previsão não pode ser menor que de aluguel");
        }

        if(aluguelRequestDTO.getDataDevolucao() != null) {
            if (aluguelRequestDTO.getDataAluguel().isAfter(aluguelRequestDTO.getDataDevolucao())) {
                throw new AluguelDataNotValidException("Data de devolução não pode ser menor que de aluguel");
            }
        }

        Aluguel aluguelToCreate = aluguelMapper.toModel(aluguelRequestDTO);

        if(aluguelToCreate.getDataDevolucao() == null) {
            if(foundLivro.getQuantidade() == 0) {
                throw new LivroInsufficientQuantity("Livro with insufficient quantity");
            }
            foundLivro.setQuantidade(foundLivro.getQuantidade() - 1);
            livroService.update(livroMapper.toRequestDTO((foundLivro)));
        }

        aluguelToCreate.setLivro(foundLivro);
        aluguelToCreate.setUsuario(foundUsuario);
        Aluguel aluguelCreated = aluguelRepository.save(aluguelToCreate);

        return aluguelMapper.toDTO(aluguelCreated);
    }

    public AluguelResponseDTO update(AluguelRequestDTO aluguelRequestDTO) {
        Livro foundLivro = livroService.verifyAndGetIfExists(aluguelRequestDTO.getLivroId());
        Usuario foundUsuario = usuarioService.verifyAndGetIfExists(aluguelRequestDTO.getUsuarioId());

        Aluguel aluguelToUpdate = aluguelMapper.toModel(aluguelRequestDTO);

        if(aluguelToUpdate.getDataDevolucao() == null) {
            if(foundLivro.getQuantidade() == 0) {
                throw new LivroInsufficientQuantity("Livro with insufficient quantity");
            }
            foundLivro.setQuantidade(foundLivro.getQuantidade() - 1);
            livroService.update(livroMapper.toRequestDTO((foundLivro)));
        }

        if(aluguelToUpdate.getDataDevolucao() != null) {
            foundLivro.setQuantidade(foundLivro.getQuantidade() + 1);
            livroService.update(livroMapper.toRequestDTO((foundLivro)));
        }

        aluguelToUpdate.setLivro(foundLivro);
        aluguelToUpdate.setUsuario(foundUsuario);
        Aluguel aluguelUpdated = aluguelRepository.save(aluguelToUpdate);

        return aluguelMapper.toDTO(aluguelUpdated);
    }

    public void deleteById(Long id) {
        aluguelRepository.deleteById(id);
    }

    public Aluguel verifyAndGetIfExists(Long id) {
        return aluguelRepository.findById(id).orElseThrow(() -> new AluguelNotFoundException(id));
    }
}
