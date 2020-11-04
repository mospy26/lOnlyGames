package com.lOnlyGames.backend.errorhandlers.exceptions;

public class InvalidAvailabilityException extends RuntimeException{
    public InvalidAvailabilityException() {
        super("Invalid Availability.");
    }
}