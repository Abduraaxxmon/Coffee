package com.example.democoffee.service.Impl;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String userNotFound) {
    }
    public ResourceNotFoundException(String message, Throwable cause){

    }
}
