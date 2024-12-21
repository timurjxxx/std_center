package com.backend.studycenter.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Could not validate!")
public class NotValidException extends Exception {
    public NotValidException(String message) {
        super(message);
    }
}
