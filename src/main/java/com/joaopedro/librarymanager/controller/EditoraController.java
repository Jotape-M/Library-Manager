package com.joaopedro.librarymanager.controller;

import com.joaopedro.librarymanager.model.Editora;
import com.joaopedro.librarymanager.repository.EditoraRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/editoras")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping
    @ApiOperation(value = "Retorna todas as editoras")
    public List<Editora> findAll() {
        return editoraRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma editora")
    public Editora create(@RequestBody @Valid Editora editora){
        return editoraRepository.save(editora);
    }

    @DeleteMapping
    @ApiOperation(value = "Deleta uma editora")
    public void delete(@RequestBody Editora editora) {
        editoraRepository.delete(editora);
    }

    @PutMapping
    @ApiOperation(value = "Altera uma editora")
    public Editora update(@RequestBody Editora editora) {
        return editoraRepository.save(editora);
    }
}
