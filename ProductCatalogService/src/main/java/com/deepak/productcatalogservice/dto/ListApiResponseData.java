package com.deepak.productcatalogservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListApiResponseData<T> {
    private boolean success;
    private String message;
    private List<T> data;
    private Long count;
}
