package com.lOnlyGames.backend.errorhandlers.exceptions;

public class CannotReportSelfException extends RuntimeException{
    public CannotReportSelfException() {
        super("You cannot report yourself.");
    }
}
