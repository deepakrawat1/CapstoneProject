package com.deepak.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceResult<T> {
    private boolean success;
    private HttpStatus status;
    private String message;
    private T data;
}
