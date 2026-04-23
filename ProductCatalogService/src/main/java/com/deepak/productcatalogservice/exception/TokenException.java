package com.deepak.productcatalogservice.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class TokenException extends RuntimeException {
    private final List<String> errors;

    public TokenException(String error) {
        super(error);
        this.errors = List.of(error);
    }

    public TokenException(List<String> errors) {
        super("Application error");
        this.errors = errors;
    }
}
