package com.omega.currencyapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.omega.currencyapi.utils.Helper;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(ApiResponseException.class)
        public ResponseEntity<ExceptionDetails> handleApiResponseException(ApiResponseException ex, WebRequest web) {
                ExceptionDetails body = ExceptionDetails.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .exceptionClass(ApiResponseException.class.getSimpleName())
                                .uri(web.getContextPath())
                                .tracePath(Helper.generateTracePath()).build();
                return ResponseEntity.status(ex.getStatusCode())
                                .body(body);
        }

        @ExceptionHandler(FreeCurrencyApiExcuteException.class)
        public ResponseEntity<ExceptionDetails> handleFreeCurrencyApiExcuteException(Exception ex, WebRequest web) {
                ExceptionDetails body = ExceptionDetails.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .exceptionClass(FreeCurrencyApiExcuteException.class.getSimpleName())
                                .uri(web.getContextPath())
                                .tracePath(Helper.generateTracePath()).build();
                return ResponseEntity
                                .status(FreeCurrencyApiExcuteException.class.getAnnotation(ResponseStatus.class).code())
                                .body(body);
        }

        @ExceptionHandler(InvalidCurrencyCodeException.class)
        public ResponseEntity<ExceptionDetails> handleInvalidCurrencyCodeException(InvalidCurrencyCodeException ex,
                        WebRequest web) {
                ExceptionDetails body = ExceptionDetails.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .exceptionClass(InvalidCurrencyCodeException.class.getSimpleName())
                                .uri(web.getContextPath())
                                .tracePath(Helper.generateTracePath()).build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(body);
        }

        @SuppressWarnings("null")
        @Override
        public  ResponseEntity<Object> handleHandlerMethodValidationException(
			HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                                ExceptionDetails body = ExceptionDetails.builder()
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .exceptionClass(HandlerMethodValidationException.class.getSimpleName())
                                .uri(request.getContextPath())
                                .tracePath(Helper.generateTracePath()).build();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(body);
	}
}
