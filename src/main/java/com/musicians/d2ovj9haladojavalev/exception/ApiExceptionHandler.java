package com.musicians.d2ovj9haladojavalev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException e) {
        // 1 Create payload containing exception details
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        // 2 Return response entity
        return new ResponseEntity<>(apiException, notFound);
    }

    // TODO: ehelyett httpmessagenotreadable dobódik
    @ExceptionHandler(value = {ValidationErrorException.class})
    public ResponseEntity<Object> handleValidationErrorException(ValidationErrorException e) {
        HttpStatus validationError = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                validationError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, validationError);
    }
}
