package com.rating.ratingreviewservice.exceptions;


import com.rating.ratingreviewservice.models.ApiError;
import com.rating.ratingreviewservice.utils.Constant;
import com.rating.ratingreviewservice.utils.ResponseHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                   WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        return ResponseHandler.generateErrorResponse(ex, HttpStatus.BAD_REQUEST, error);
    }


    @ExceptionHandler
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex,
                                                                   WebRequest request) {
        String error =  ex.getLocalizedMessage();
        return ResponseHandler.generateErrorResponse(ex, HttpStatus.BAD_REQUEST, error);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUnauthorizedException(UnAuthorizedException ex,
                                                            WebRequest request) {
        String error = ex.getLocalizedMessage();
        return ResponseHandler.generateErrorResponse(ex, HttpStatus.UNAUTHORIZED, error);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request){
        return ResponseHandler.generateErrorResponse(ex, HttpStatus.NOT_FOUND, Constant.NOT_FOUND_ERROR_MESSAGE);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleInternalServerException(InternalServerException ex, WebRequest request){
        return ResponseHandler.generateErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR,
                Constant.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        return ResponseHandler.generateErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR,
                Constant.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status,
                                                                          WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return ResponseHandler.generateErrorResponse(ex, HttpStatus.BAD_REQUEST, error);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        return ResponseHandler.generateErrorResponse(ex, HttpStatus.NOT_FOUND, error);
    }

}
