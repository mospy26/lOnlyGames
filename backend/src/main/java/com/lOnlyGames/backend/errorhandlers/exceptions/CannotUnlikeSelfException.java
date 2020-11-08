package com.lOnlyGames.backend.errorhandlers.exceptions;

public class CannotUnlikeSelfException extends RuntimeException{
    public CannotUnlikeSelfException() {
        super("You cannot unlike yourself.");
    }
}
