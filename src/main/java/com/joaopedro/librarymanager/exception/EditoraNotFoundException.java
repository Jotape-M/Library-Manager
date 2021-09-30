package com.joaopedro.librarymanager.exception;

import javax.persistence.EntityNotFoundException;

public class EditoraNotFoundException extends EntityNotFoundException {

    public EditoraNotFoundException(Long id) {
        super(String.format("Editora com o id %s não existe", id));
    }
}
