package com.trl.libraryservice.exception;

import com.trl.libraryservice.controller.dto.ErrorDetailsDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalValueException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleIllegalValueException(IllegalValueException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}