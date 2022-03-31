package com.rating.ratingreviewservice.exceptions;


public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {

        super(message);
    }
}
