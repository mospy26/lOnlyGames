package com.lOnlyGames.backend.errorhandlers.exceptions;

public class UserAlreadyDislikedException extends RuntimeException{
    public UserAlreadyDislikedException(){ super("You cannot dislike a user you have not liked."); }
}
