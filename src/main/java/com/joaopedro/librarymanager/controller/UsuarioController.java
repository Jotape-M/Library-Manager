package com.joaopedro.librarymanager.controller;

import com.joaopedro.librarymanager.dto.UsuarioDTO;
import com.joaopedro.librarymanager.service.UsuarioService;
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
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ApiOperation(value = "Rertorna uma lista de usuarios")
    public ResponseEntity<Page<UsuarioDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(usuarioService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cria um usuario")
    public UsuarioDTO create(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return usuarioService.create(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um usuario")
    public void delete(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @PutMapping
    @ApiOperation(value = "Altera um usuario")
    public UsuarioDTO update(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return usuarioService.update(usuarioDTO);
    }
}
