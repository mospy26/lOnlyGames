package com.lOnlyGames.backend.errorhandlers.exceptions;

public class UserAlreadyLikedException extends RuntimeException{
    public UserAlreadyLikedException(){ super("You cannot like a user you have already liked."); }
}
