package com.trl.libraryservice.exception;

import com.trl.libraryservice.controller.dto.ErrorDetailsDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalValueException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleIllegalValueException(IllegalValueException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExistException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleUserNotExistException(UserNotExistException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotExistException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleBookNotExistException(BookNotExistException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentNotExistException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleCommentNotExistException(CommentNotExistException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SubCommentNotExistException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleSubCommentNotExistException(SubCommentNotExistException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TheSameValueException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleTheSameValueException(TheSameValueException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FunctionalityNotImplementedException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleFunctionalityNotImplementedException(FunctionalityNotImplementedException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<ErrorDetailsDTO> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setErrorMessage(ex.getMessage());
        errorDetails.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setDescription(request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}