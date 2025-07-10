package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.tomtomDtos.NearbySearchResponse;

public interface TomtomApiService {

    NearbySearchResponse findPlaces(String latitude, String longitude);

}
