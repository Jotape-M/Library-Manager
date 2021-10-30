package com.joaopedro.librarymanager.exception.usuario;

import javax.persistence.EntityNotFoundException;

public class UsuarioNotFoundException extends EntityNotFoundException {

    public UsuarioNotFoundException(Long id) {
        super(String.format("Usuario com o id %s não existe", id));
    }
}
