package com.deepak.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseData<T> {
    private boolean success;
    private String message;
    private T data;
}
