package com.lOnlyGames.backend.errorhandlers.exceptions;

public class CannotUnblockSelfException extends RuntimeException{
    public CannotUnblockSelfException() {
        super("You cannot report yourself.");
    }
}
