package com.example.imagedata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ImageAlreadyExistingException extends Exception {

    public ImageAlreadyExistingException(String message) {
        super(message);
    }

}
