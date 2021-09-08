package com.example.demodocker.exceptions;


public class CannotDeleteUserException extends RuntimeException {
    public CannotDeleteUserException(String message) {
        super(message);
    }
}
