package com.cdantas.league.infra.exception;

public class TeamNotFound extends RuntimeException {
    public TeamNotFound(String message) {
        super(message);
    }
}
