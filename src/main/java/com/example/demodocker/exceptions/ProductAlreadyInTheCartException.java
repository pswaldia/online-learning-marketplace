package com.example.demodocker.exceptions;

public class ProductAlreadyInTheCartException extends RuntimeException {
    public ProductAlreadyInTheCartException(String message) {
        super(message);
    }

}
