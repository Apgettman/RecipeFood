package com.skypro.recipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RecipeObjectNotFoundException extends RuntimeException {
    public RecipeObjectNotFoundException(String message) {
        super(message);
    }
}
