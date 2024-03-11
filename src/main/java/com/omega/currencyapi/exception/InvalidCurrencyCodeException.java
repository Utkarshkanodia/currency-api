package com.omega.currencyapi.exception;

public class InvalidCurrencyCodeException extends RuntimeException {

    InvalidCurrencyCodeException(String message){
        super(message);
    }

}
