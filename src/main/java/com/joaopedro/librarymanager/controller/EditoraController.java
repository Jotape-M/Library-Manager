package com.joaopedro.librarymanager.controller;

import com.joaopedro.librarymanager.dto.EditoraDTO;
import com.joaopedro.librarymanager.service.EditoraService;
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
    private EditoraService editoraService;

    @GetMapping
    @ApiOperation(value = "Retorna todas as editoras")
    public List<EditoraDTO> findAll() {
        return editoraService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria uma editora")
    public EditoraDTO create(@RequestBody @Valid EditoraDTO editoraDTO){
        return editoraService.create(editoraDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta uma editora")
    public void deleteById(@PathVariable Long id) {
        editoraService.deleteById(id);
    }

    @PutMapping
    @ApiOperation(value = "Altera uma editora")
    public EditoraDTO update(@RequestBody EditoraDTO editoraDTO) {
        return editoraService.update(editoraDTO);
    }
}
