package com.lOnlyGames.backend.errorhandlers.exceptions;

public class CannotBlockSelfException extends RuntimeException{
    public CannotBlockSelfException() {
        super("You cannot report yourself.");
    }
}
