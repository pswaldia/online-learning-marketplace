package com.example.demodocker.exceptions;

public class RoleAlreadyPresentException extends RuntimeException {
    public RoleAlreadyPresentException(String message) {
        super(message);
    }
}
