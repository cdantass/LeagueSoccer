package com.cdantas.league.infra.exception;

public class TooManyLoginAttempts extends RuntimeException {
    public TooManyLoginAttempts(String message) {
        super(message);
    }
}
