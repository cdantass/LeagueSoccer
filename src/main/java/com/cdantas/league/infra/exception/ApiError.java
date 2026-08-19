package com.cdantas.league.infra.exception;

import java.time.Instant;
import java.util.List;

public record ApiError(Instant timestamp, int status, String error, String message, List<FieldErrorDetail> fields) {

    public ApiError(int status, String error, String message){
        this(Instant.now(), status, error, message, null);
    }

    public ApiError(int status, String error, String message, List<FieldErrorDetail> fields){
        this(Instant.now(), status, error, message, fields);
    }

    public record FieldErrorDetail(String field, String message){}
}
