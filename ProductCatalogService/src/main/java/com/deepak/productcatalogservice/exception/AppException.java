package com.deepak.productcatalogservice.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class AppException extends RuntimeException{
    private final List<String> errors;

    public AppException(String error) {
        super(error);
        this.errors = List.of(error);
    }

    public AppException(List<String> errors) {
        super("Application error");
        this.errors = errors;
    }
}
