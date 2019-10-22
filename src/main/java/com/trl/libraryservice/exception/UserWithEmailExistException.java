package com.trl.libraryservice.exception;

public class UserWithEmailExistException extends Exception{

    public UserWithEmailExistException(String message) {
        super(message);
    }
}
