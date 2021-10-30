package com.joaopedro.librarymanager.exception.editora;

import com.joaopedro.librarymanager.exception.EntityCanNotBeDeletedException;

public class EditoraCanNotBeDeletedException extends EntityCanNotBeDeletedException {

    public EditoraCanNotBeDeletedException(Long editoraId, Long livroId) {
        super(String.format("Editora with id %s cannot be deleted because it is related to a livro with id %s", editoraId, livroId));
    }
}
