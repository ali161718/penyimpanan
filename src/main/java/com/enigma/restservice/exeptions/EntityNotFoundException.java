package com.enigma.restservice.exeptions;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException() {
        super("exception.entity.not.found", ErrorCodes.ENTITY_NOT_FOUND);
    }
}
