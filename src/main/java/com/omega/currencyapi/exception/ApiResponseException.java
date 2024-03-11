package com.omega.currencyapi.exception;

import lombok.Getter;

@Getter
public class ApiResponseException extends RuntimeException {

    private int statusCode;

    public ApiResponseException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
