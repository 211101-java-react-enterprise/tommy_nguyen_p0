package com.revature.banking.exceptions;

public class OverdraftException extends RuntimeException {
    public OverdraftException(String message) {
        super(message);
    }
}
