package com.lOnlyGames.backend.errorhandlers.exceptions;

public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException() {
        super("Invalid username");
    }
}
