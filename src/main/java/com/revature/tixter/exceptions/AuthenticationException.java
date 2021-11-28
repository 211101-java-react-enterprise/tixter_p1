package com.revature.tixter.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Incorrect credentials provided. Could not authenticate.");
    }
}
