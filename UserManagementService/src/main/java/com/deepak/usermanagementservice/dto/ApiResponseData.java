package com.deepak.usermanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseData<T> {
    private boolean success;
    private String message;
    private T data;
}
