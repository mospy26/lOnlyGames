package com.lOnlyGames.backend.errorhandlers.exceptions;

public class AvailabilityOverlapException extends RuntimeException {
    public AvailabilityOverlapException() {
        super("This availability's time overlaps with an existing availability. Remove that availability or change the time.");
    }
}
