package com.tisitha.weather_based_travel_helper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LocationNotFoundException.class)
    public ProblemDetail locationNotFoundExceptionHandler(LocationNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ExternalApiException.class)
    public ProblemDetail externalApiExceptionHandler(ExternalApiException ex){
        return ProblemDetail.forStatusAndDetail(ex.getStatus(),ex.getMessage());
    }

    @ExceptionHandler(InvalidDateException.class)
    public ProblemDetail invalidDateExceptionHandler(InvalidDateException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
