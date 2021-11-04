package com.joaopedro.librarymanager.controller;

import com.joaopedro.librarymanager.dto.request.LivroRequestDTO;
import com.joaopedro.librarymanager.dto.response.LivroResponseDTO;
import com.joaopedro.librarymanager.service.impl.LivroServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroServiceImpl livroService;

    @GetMapping
    @ApiOperation(value = "Retorna todos os livros")
    public ResponseEntity<Page<LivroResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(livroService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um livro")
    public LivroResponseDTO create(@RequestBody @Valid LivroRequestDTO livroRequestDTO) {
        return livroService.create(livroRequestDTO);
    }

    @PutMapping
    @ApiOperation(value = "Altera um livro")
    public LivroResponseDTO update(@RequestBody @Valid LivroRequestDTO livroRequestDTO) {
        return livroService.update(livroRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um livro")
    public void delete(@PathVariable Long id) {
        livroService.deleteById(id);
    }
}
