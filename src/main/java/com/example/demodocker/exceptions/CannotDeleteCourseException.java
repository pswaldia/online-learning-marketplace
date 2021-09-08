package com.example.demodocker.exceptions;

public class CannotDeleteCourseException extends RuntimeException {
    public CannotDeleteCourseException(String message) {
        super(message);
    }
}
