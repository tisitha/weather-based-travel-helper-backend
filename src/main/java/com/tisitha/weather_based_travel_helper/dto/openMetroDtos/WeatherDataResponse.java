package com.tisitha.weather_based_travel_helper.dto.openMetroDtos;

import lombok.Data;

@Data
public class WeatherDataResponse {

    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private HourlyUnits hourly_units;
    private Hourly hourly;
    private DailyUnits daily_units;
    private Daily daily;

}
