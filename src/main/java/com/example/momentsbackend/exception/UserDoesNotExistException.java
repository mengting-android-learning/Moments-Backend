package com.example.momentsbackend.exception;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String errorMessage) {
        super("this user does not exist " + errorMessage);
    }
}
