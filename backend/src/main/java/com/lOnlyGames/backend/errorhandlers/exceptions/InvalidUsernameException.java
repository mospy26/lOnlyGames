package com.lOnlyGames.backend.errorhandlers.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException() {
        super("Invalid username");
    }
}
