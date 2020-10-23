package com.lOnlyGames.backend.errorhandlers.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Bad credentials");
    }
}
