package com.tisitha.weather_based_travel_helper.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class ExternalApiException extends RuntimeException{

    private final HttpStatus status;

    public ExternalApiException(String message, HttpStatusCode statusCode){
        super(message);
        this.status = HttpStatus.valueOf(statusCode.value());
    }

}
