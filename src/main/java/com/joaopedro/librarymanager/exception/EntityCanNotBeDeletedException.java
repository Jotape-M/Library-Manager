package com.joaopedro.librarymanager.exception;


import javax.persistence.PersistenceException;

public class EntityCanNotBeDeletedException extends PersistenceException {

    public EntityCanNotBeDeletedException() {
        super();
    }

    public EntityCanNotBeDeletedException(String message) {
        super(message);
    }
}
