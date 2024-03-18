package com.wipro.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(CustomBadRequestException badRequestException, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), badRequestException.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), resourceNotFoundException.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
