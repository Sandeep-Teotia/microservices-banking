package com.udemy.cards.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.udemy.cards.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,
            WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
