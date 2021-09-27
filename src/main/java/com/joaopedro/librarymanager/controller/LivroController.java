package com.joaopedro.librarymanager.controller;

import com.joaopedro.librarymanager.dto.LivroDTO;
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
    public List<LivroDTO> findAll() {
        return livroService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um livro")
    public LivroDTO create(@RequestBody @Valid LivroDTO livroDTO) {
        return livroService.create(livroDTO);
    }


}
