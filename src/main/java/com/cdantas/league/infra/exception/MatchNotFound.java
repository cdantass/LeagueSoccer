package com.cdantas.league.infra.exception;

public class MatchNotFound extends RuntimeException {
    public MatchNotFound(String message) {
        super(message);
    }
}
