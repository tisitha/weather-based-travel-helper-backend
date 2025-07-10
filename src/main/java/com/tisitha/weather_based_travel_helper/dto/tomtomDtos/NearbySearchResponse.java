package com.tisitha.weather_based_travel_helper.dto.tomtomDtos;

import lombok.Data;

import java.util.List;

@Data
public class NearbySearchResponse {

    private Summary summary;
    private List<Result> results;
}
