package com.lOnlyGames.backend.errorhandlers.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Bad credentials");
    }
}
