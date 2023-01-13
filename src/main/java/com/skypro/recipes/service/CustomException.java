package com.skypro.recipes.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class CustomException extends RuntimeException {
    public CustomException(String message) {

        super(message);
    }
}