package com.tisitha.weather_based_travel_helper.dto.openMetroDtos;

import lombok.Data;

@Data
public class DailyUnits {

    private String time;
    private String precipitation_sum;
    private String temperature_2m_max;

}
