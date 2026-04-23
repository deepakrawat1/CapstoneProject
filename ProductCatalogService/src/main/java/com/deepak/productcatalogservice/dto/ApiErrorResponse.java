package com.deepak.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiErrorResponse {
    private boolean success;
    private List<String> errors;
}
