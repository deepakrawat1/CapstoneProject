package com.deepak.usermanagementservice.exception;

import com.deepak.usermanagementservice.dto.ApiErrorResponse;
import com.deepak.usermanagementservice.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // handle dto validation error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage()) // 👈 YOUR MESSAGE HERE
                .toList();

        return ResponseUtil.error(errors,  HttpStatus.BAD_REQUEST);
    }

    // Handle all rest error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex) {

        // for custom error
        if (ex instanceof AppException appEx) {
            return ResponseUtil.error(appEx.getErrors(), HttpStatus.BAD_REQUEST);
        }
        else if(ex instanceof TokenException jwtEx){
            return ResponseUtil.error(jwtEx.getErrors(), HttpStatus.FORBIDDEN);
        }

        log.error(ex.getMessage(), ex);
        // rest error
        return ResponseUtil.error(
                "Something went wrong",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
