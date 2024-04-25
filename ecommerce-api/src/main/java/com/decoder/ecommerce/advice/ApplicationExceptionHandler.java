package com.decoder.ecommerce.advice;

import com.decoder.ecommerce.exception.CartItemException;
import com.decoder.ecommerce.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // status code: 401
    @ExceptionHandler(UserException.class)
    public Map<String, String> handleUserException(UserException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Status Code:", "401");
        errorMap.put("Error Type:", "UNAUTHORIZED");
        errorMap.put("Error Message: ", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CartItemException.class)
    public Map<String, String> handleCartItemException(CartItemException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Status Code:", "500");
        errorMap.put("Error Type:", "INTERNAL_SERVER_ERROR");
        errorMap.put("Error Message: ", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Status Code:", "400");
        errorMap.put("Error Type:", "BAD_REQUEST");
        errorMap.put("Error Message: ", ex.getMessage());
        return errorMap;
    }
}
