package com.skypro.recipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RecipeFileSavingException extends RuntimeException {

    public RecipeFileSavingException(String message) {

        super(message);
    }
}
