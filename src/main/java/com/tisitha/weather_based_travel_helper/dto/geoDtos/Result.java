package com.tisitha.weather_based_travel_helper.dto.geoDtos;

import lombok.Data;

@Data
public class Result {

    private int id;
    private String name;
    private String latitude;
    private String longitude;
    private double elevation;
    private String feature_code;
    private String country_code;
    private int admin1_id;
    private int admin2_id;
    private int admin3_id;
    private String timezone;
    private int population;
    private int country_id;
    private String country;
    private String admin1;
    private String admin2;
    private String admin3;

}
