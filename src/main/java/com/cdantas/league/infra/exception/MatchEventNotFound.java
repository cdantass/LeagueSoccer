package com.cdantas.league.infra.exception;

public class MatchEventNotFound extends RuntimeException {
    public MatchEventNotFound(String message) {
        super(message);
    }
}
