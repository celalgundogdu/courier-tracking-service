package com.celalgundogdu.couriertracking.common.exception.handler;

import com.celalgundogdu.couriertracking.common.exception.EntityNotExistsException;
import com.celalgundogdu.couriertracking.common.exception.ExceptionResponse;
import com.celalgundogdu.couriertracking.common.exception.ValidationExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ValidationExceptionResponse exceptionResponse = new ValidationExceptionResponse(
                request.getServletPath(),
                "Validation exception",
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                errors
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getServletPath(),
                exception.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotExistsException(EntityNotExistsException exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getServletPath(),
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception, HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                request.getServletPath(),
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
