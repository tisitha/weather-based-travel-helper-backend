package com.tisitha.weather_based_travel_helper.exception;

public class LocationNotFoundException extends RuntimeException{

    public LocationNotFoundException(String message){
        super(message);
    }
}
