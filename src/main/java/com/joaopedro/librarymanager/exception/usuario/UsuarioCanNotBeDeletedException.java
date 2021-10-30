package com.joaopedro.librarymanager.exception.usuario;

import com.joaopedro.librarymanager.exception.EntityCanNotBeDeletedException;

public class UsuarioCanNotBeDeletedException extends EntityCanNotBeDeletedException {

    public UsuarioCanNotBeDeletedException(Long usuarioId, Long aluguelId) {
        super(String.format("Usuário with id %s cannot be deleted because it is related to a aluguel with id %s", usuarioId, aluguelId));
    }
}
