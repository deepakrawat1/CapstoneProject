package com.deepak.usermanagementservice.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class AppException extends RuntimeException{
    private final List<String> errors;

    public AppException(String message) {
        super(message);
        this.errors = List.of(message);
    }

    public AppException(List<String> errors) {
        super("Application error");
        this.errors = errors;
    }
}
