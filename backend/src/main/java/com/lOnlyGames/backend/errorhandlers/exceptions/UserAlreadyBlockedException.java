package com.lOnlyGames.backend.errorhandlers.exceptions;

public class UserAlreadyBlockedException extends RuntimeException{
    public UserAlreadyBlockedException(){ super("You have already blocked this user."); }
}
