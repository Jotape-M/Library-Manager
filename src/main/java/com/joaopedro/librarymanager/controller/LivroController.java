package com.joaopedro.librarymanager.controller;

import com.joaopedro.librarymanager.dto.request.LivroRequestDTO;
import com.joaopedro.librarymanager.dto.response.LivroResponseDTO;
import com.joaopedro.librarymanager.service.LivroService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    @ApiOperation(value = "Retorna todos os livros")
    public List<LivroResponseDTO> findAll() {
        return livroService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um livro")
    public LivroResponseDTO create(@RequestBody @Valid LivroRequestDTO livroRequestDTO) {
        return livroService.create(livroRequestDTO);
    }

    @PutMapping
    @ApiOperation(value = "Altera um livro")
    public LivroResponseDTO update(@RequestBody LivroRequestDTO livroRequestDTO) {
        return livroService.update(livroRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um livro")
    public void delete(@PathVariable Long id) {
        livroService.deleteById(id);
    }
}
