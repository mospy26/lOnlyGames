package com.lOnlyGames.backend.errorhandlers.exceptions;

public class APICallFailedException extends RuntimeException {

    public APICallFailedException(int requestCode){super("The API call has failed, the request code is" + requestCode);}

}
