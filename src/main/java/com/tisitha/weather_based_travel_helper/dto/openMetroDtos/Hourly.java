package com.tisitha.weather_based_travel_helper.dto.openMetroDtos;

import lombok.Data;

import java.util.List;

@Data
public class Hourly {

    private List<String> time;
    private List<Double> precipitation;

}
