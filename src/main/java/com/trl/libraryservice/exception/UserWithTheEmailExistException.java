package com.trl.libraryservice.exception;

public class UserWithTheEmailExistException extends Exception{

    public UserWithTheEmailExistException(String message) {
        super(message);
    }
}
