package com.joaopedro.librarymanager.exception.livro;

import javax.persistence.PersistenceException;

public class LivroInsufficientQuantity extends PersistenceException {

    public LivroInsufficientQuantity() {
        super();
    }

    public LivroInsufficientQuantity(String message) {
        super(message);
    }
}
