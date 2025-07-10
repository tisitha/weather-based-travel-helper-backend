package com.tisitha.weather_based_travel_helper.dto.geoDtos;

import lombok.Data;

import java.util.List;

@Data
public class GeoLocationResponse {

    private List<Result> results;
    private double generationtime_ms;

}
