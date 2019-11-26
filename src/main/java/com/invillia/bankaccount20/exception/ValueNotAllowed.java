package com.invillia.bankaccount20.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValueNotAllowed extends RuntimeException {
    public ValueNotAllowed(String message) {
        super(message);
    }
}
