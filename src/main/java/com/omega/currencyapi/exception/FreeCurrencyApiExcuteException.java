package com.omega.currencyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class FreeCurrencyApiExcuteException extends RuntimeException{

    public FreeCurrencyApiExcuteException(String message){
        super(message);
    }

}
