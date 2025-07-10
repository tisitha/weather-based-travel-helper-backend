package com.tisitha.weather_based_travel_helper.dto.tomtomDtos;

import lombok.Data;

@Data
public class Summary {

    private String queryType;
    private int queryTime;
    private int numResults;
    private int offset;
    private int totalResults;
    private int fuzzyLevel;
    private GeoBias geoBias;

}
