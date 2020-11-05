package com.lOnlyGames.backend.errorhandlers.exceptions;

public class UserAlreadyUnblockedException extends RuntimeException{
    public UserAlreadyUnblockedException(){ super("You have not blocked this user."); }
}
