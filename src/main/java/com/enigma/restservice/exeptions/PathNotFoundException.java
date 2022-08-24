package com.enigma.restservice.exeptions;

public class PathNotFoundException extends ApplicationException {

    public PathNotFoundException() {
        super("exception.path.not.found", ErrorCodes.PATH_NOT_FOUND);
    }
}
