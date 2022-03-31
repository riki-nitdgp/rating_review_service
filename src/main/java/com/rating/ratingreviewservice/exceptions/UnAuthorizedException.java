package com.rating.ratingreviewservice.exceptions;


public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message) {
        super(message);
    }
}
