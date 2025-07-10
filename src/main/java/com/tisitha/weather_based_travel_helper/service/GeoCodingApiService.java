package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.geoDtos.GeoLocationResponse;

public interface GeoCodingApiService {

    GeoLocationResponse searchPlace(String placeName);

}
