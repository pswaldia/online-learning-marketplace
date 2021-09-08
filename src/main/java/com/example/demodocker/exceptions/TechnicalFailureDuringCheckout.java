package com.example.demodocker.exceptions;

public class TechnicalFailureDuringCheckout extends RuntimeException {
    public TechnicalFailureDuringCheckout(String message){
        super(message);
    }
}
