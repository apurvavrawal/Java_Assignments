package com.josh.hotelmgmt.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FoodNotAvailableException extends RuntimeException {
    public FoodNotAvailableException(String message){
        super(message);
    }
}
