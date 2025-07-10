package com.tisitha.weather_based_travel_helper.dto.openMetroDtos;

import lombok.Data;

import java.util.List;

@Data
public class Daily {

    private List<String> time;
    private List<Double> precipitation_sum;
    private List<Double> temperature_2m_max;

}
