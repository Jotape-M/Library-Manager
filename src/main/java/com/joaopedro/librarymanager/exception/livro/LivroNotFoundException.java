package com.joaopedro.librarymanager.exception.livro;

import javax.persistence.EntityNotFoundException;

public class LivroNotFoundException extends EntityNotFoundException {

    public LivroNotFoundException(Long id) {
        super(String.format("Livro com o id %s não existe", id));
    }
}
