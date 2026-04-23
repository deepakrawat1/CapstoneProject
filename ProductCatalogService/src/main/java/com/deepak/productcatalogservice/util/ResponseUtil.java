package com.deepak.productcatalogservice.util;

import com.deepak.productcatalogservice.dto.ApiErrorResponse;
import com.deepak.productcatalogservice.dto.ApiResponse;
import com.deepak.productcatalogservice.dto.ApiResponseData;
import com.deepak.productcatalogservice.dto.ListApiResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {

    public static <T> ResponseEntity<ListApiResponseData<T>> success(List<T> data, Long count, HttpStatus status) {

        ListApiResponseData<T> apiResponse = new ListApiResponseData<>();

        apiResponse.setCount(count);
        apiResponse.setData(data);
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Request processed successfully");

        return ResponseEntity.status(status).body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponseData<T>> success(T data, HttpStatus status) {

        ApiResponseData<T> apiResponse = new ApiResponseData<>();

        apiResponse.setData(data);
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Request processed successfully");

        return ResponseEntity.status(status).body(apiResponse);
    }

    public static ResponseEntity<ApiResponse> success(HttpStatus status) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Request processed successfully");

        return ResponseEntity.status(status).body(apiResponse);
    }

    public static ResponseEntity<ApiResponse> success(HttpStatus status, String message) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage(message);

        return ResponseEntity.status(status).body(apiResponse);
    }

    public static ResponseEntity<ApiErrorResponse> error(List<String> errors, HttpStatus status) {

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setErrors(errors);
        apiErrorResponse.setSuccess(false);

        return ResponseEntity.status(status).body(apiErrorResponse);
    }

    public static ResponseEntity<ApiErrorResponse> error(String error, HttpStatus status) {
        return error(List.of(error), status);
    }
}
