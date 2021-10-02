package com.joaopedro.librarymanager.controller;

import com.joaopedro.librarymanager.dto.request.AluguelRequestDTO;
import com.joaopedro.librarymanager.dto.response.AluguelResponseDTO;
import com.joaopedro.librarymanager.service.AluguelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    @ApiOperation(value = "Retorna todos os alugueis")
    public List<AluguelResponseDTO> findAll() {
        return aluguelService.findAll();
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
    @ApiOperation(value = "Deleta um aluguel")
    public void delete(@PathVariable Long id) {
        aluguelService.deleteById(id);
    }
}
