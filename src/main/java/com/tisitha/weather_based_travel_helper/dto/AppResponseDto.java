package com.tisitha.weather_based_travel_helper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResponseDto {

    private String placeName;

    private String maxTemp;

    private String precipitationSum;

    private String latitude;

    private String longitude;

    private List<String> precipitationHourly;

}
