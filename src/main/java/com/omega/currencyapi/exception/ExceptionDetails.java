package com.omega.currencyapi.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExceptionDetails {
    private LocalDateTime timestamp;
    private String message;
    private String uri;
    private String exceptionClass;
    private String tracePath;
}
