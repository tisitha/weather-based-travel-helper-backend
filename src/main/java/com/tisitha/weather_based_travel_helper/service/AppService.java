package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.AppResponseDto;

import java.util.List;

public interface AppService {

    List<AppResponseDto> getData(String placeName, String date);

}
