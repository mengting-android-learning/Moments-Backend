package com.example.momentsbackend.exception;

public class UserNameExistsException extends Exception {
    public UserNameExistsException(String errorMessage) {
        super("user name has already exists:" + errorMessage);
    }
}
