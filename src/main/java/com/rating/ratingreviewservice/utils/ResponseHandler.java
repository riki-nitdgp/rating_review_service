package com.rating.ratingreviewservice.utils;


import com.rating.ratingreviewservice.models.ApiError;
import com.rating.ratingreviewservice.models.Paginator;
import com.rating.ratingreviewservice.models.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(Object responseObj,  Paginator pageInfo) {
        HttpStatus status = HttpStatus.OK;
        boolean isSuccess = true;
        Response response = new Response(responseObj, isSuccess, status.value(), pageInfo);
        return new ResponseEntity<Object>(response, status);
    }

    public static ResponseEntity<Object> generateResponse(Object responseObj) {
        HttpStatus status = HttpStatus.OK;
        boolean isSuccess = true;
        Response response = new Response(responseObj, isSuccess, status.value());
        return new ResponseEntity<Object>(response, status);
    }

    public static ResponseEntity<Object> generateErrorResponse(Exception ex, HttpStatus httpStatus, String error) {
        ApiError apiError = new ApiError(httpStatus.value(), ex.getMessage(), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), httpStatus);
    }

    public static ResponseEntity<Object> generateErrorResponse(Exception ex, HttpStatus httpStatus, List errors) {
        ApiError apiError = new ApiError(httpStatus.value(), ex.getMessage(), errors);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
