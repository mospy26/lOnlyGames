package com.lOnlyGames.backend.errorhandlers;

import java.time.LocalDateTime;

import com.lOnlyGames.backend.errorhandlers.ErrorCodes.ErrorCode;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidCredentialsException;
import com.lOnlyGames.backend.errorhandlers.exceptions.InvalidUsernameException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionResponse> usernameNotFound(UsernameNotFoundException ex) {
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.INVALID_USERNAME, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<ExceptionResponse> invalidUsername(InvalidUsernameException ex) {
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.INVALID_USERNAME, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidUsername(InvalidCredentialsException ex) {
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.INVALID_CREDS, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    private ExceptionResponse generateExceptionResponse(ErrorCode code, String message) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(code.name());
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());

        return response;
    }
}
