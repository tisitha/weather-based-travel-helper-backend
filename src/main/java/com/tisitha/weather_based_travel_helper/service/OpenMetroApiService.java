package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.openMetroDtos.WeatherDataResponse;

public interface OpenMetroApiService {

    WeatherDataResponse getWeather(String latitude, String longitude, String date);

}
