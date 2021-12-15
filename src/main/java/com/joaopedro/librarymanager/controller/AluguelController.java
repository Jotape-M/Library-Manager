package com.joaopedro.librarymanager.controller;

import com.joaopedro.librarymanager.dto.request.AluguelRequestDTO;
import com.joaopedro.librarymanager.dto.response.AluguelResponseDTO;
import com.joaopedro.librarymanager.service.IAluguelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/alugueis")
public class AluguelController {

    private final IAluguelService aluguelService;

    @Autowired
    public AluguelController(IAluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de alugueis paginada")
    public ResponseEntity<Page<AluguelResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(aluguelService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Retorna uma lista de alugueis")
    public ResponseEntity<List<AluguelResponseDTO>> findAll() {
        return new ResponseEntity<>(aluguelService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Criar Aluguel")
    public AluguelResponseDTO create(@RequestBody @Valid AluguelRequestDTO aluguelRequestDTO) {
        return aluguelService.create(aluguelRequestDTO);
    }

    @PutMapping
    @ApiOperation(value = "Alterar um aluguel")
    public AluguelResponseDTO update(@RequestBody @Valid AluguelRequestDTO aluguelRequestDTO) {
        return aluguelService.update(aluguelRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um aluguel")
    public void delete(@PathVariable Long id) {
        aluguelService.deleteById(id);
    }
}
