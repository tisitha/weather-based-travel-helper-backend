package com.tisitha.weather_based_travel_helper.dto.tomtomDtos;

import lombok.Data;

import java.util.List;

@Data
public class Result {

    private String type;
    private String id;
    private double score;
    private double dist;
    private String info;
    private Poi poi;
    private Address address;
    private Position position;
    private Viewport viewport;
    private List<EntryPoint> entryPoints;

}
