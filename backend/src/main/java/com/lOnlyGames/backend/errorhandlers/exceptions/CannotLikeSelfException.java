package com.lOnlyGames.backend.errorhandlers.exceptions;

public class CannotLikeSelfException extends RuntimeException{
    public CannotLikeSelfException() {
        super("You cannot like yourself.");
    }
}
