package com.lOnlyGames.backend.errorhandlers;

import java.time.LocalDateTime;

import com.lOnlyGames.backend.errorhandlers.ErrorCodes.ErrorCode;
import com.lOnlyGames.backend.errorhandlers.exceptions.*;

import org.hibernate.procedure.ParameterMisuseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
                generateExceptionResponse(ErrorCode.INVALID_USERNAME, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidUsername(InvalidCredentialsException ex) {
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.INVALID_CREDS, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> accessDenied(AccessDeniedException ex) {
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.ACCESS_DENIED, ex.getMessage()), HttpStatus.FORBIDDEN);
    }  
    
        
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<ExceptionResponse> params(Exception ex) {
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.PARAMS_MISSING, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserAlreadyLikedException.class, UserAlreadyDislikedException.class})
    public ResponseEntity<ExceptionResponse> alreadyLiked(Exception ex) {
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.ALREADY_LIKE_DISLIKE, ex.getMessage()), HttpStatus.OK);
    }
    @ExceptionHandler({UserAlreadyBlockedException.class})
    public ResponseEntity<ExceptionResponse> alreadyBlocked(Exception ex){
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.ALREADY_BLOCKED, ex.getMessage()), HttpStatus.OK);
    }
    @ExceptionHandler({UserAlreadyUnblockedException.class})
    public ResponseEntity<ExceptionResponse> alreadyUnblocked(Exception ex){
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.ALREADY_UNBLOCKED, ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler({CannotReportSelfException.class})
    public ResponseEntity<ExceptionResponse> reportingSelf(Exception ex){
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.REPORT_SELF, ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({InvalidAvailabilityException.class})
    public ResponseEntity<ExceptionResponse> invalidAvailability(Exception ex){
        return new ResponseEntity<ExceptionResponse>(
                generateExceptionResponse(ErrorCode.INVALID_AVAILABILITY, ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    /**
     * FALLBACK *
     */
    // @ExceptionHandler(Exception.class)
    // public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
    //     return new ResponseEntity<>(generateExceptionResponse(ErrorCode.INTERNAL_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    private ExceptionResponse generateExceptionResponse(ErrorCode code, String message) {
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(code.name());
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now());

        return response;
    }
}
