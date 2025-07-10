package com.tisitha.weather_based_travel_helper.dto.tomtomDtos;

import lombok.Data;

import java.util.List;

@Data
public class Poi {

    private String name;
    private List<CategorySet> categorySet;
    private List<String> categories;
    private List<Classification> classifications;

}
