package com.joaopedro.librarymanager.exception.livro;

import com.joaopedro.librarymanager.exception.EntityCanNotBeDeletedException;

public class LivroCanNotBeDeletedException extends EntityCanNotBeDeletedException {

    public LivroCanNotBeDeletedException(Long livroId, Long aluguelId) {
        super(String.format("Livro with id %s cannot be deleted because it is related to a aluguel with id %s", livroId, aluguelId));
    }
}
