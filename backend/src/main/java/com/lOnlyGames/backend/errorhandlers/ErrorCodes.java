package com.lOnlyGames.backend.errorhandlers;

public class ErrorCodes {

    public static enum ErrorCode {
        NOT_FOUND,
        UNAUTHORIZED,
        UNAUTHENTICATED,
        INVALID_CREDS,
        INVALID_DATA,
        INVALID_USERNAME,
        ACCESS_DENIED,
        PARAMS_MISSING,
        INTERNAL_ERROR,
        ALREADY_LIKE_DISLIKE,
        ALREADY_BLOCKED,
        ALREADY_UNBLOCKED,
        REPORT_SELF,
        INVALID_AVAILABILITY
    }
    
}
