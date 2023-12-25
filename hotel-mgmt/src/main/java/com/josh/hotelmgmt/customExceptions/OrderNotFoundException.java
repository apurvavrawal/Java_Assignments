package com.josh.hotelmgmt.customExceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends Throwable{


    public OrderNotFoundException(String message) {
    }
}