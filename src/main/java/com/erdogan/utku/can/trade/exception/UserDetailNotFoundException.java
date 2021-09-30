package com.erdogan.utku.can.trade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDetailNotFoundException extends RuntimeException {
    public UserDetailNotFoundException(String message) {
        super(message);
    }
}
