package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.tomtomDtos.NearbySearchResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TomtomApiServiceImp implements TomtomApiService{

    @Value("${tomtom.api.key}")
    private String apiKey;

    private final RestClient tomtomRestClient;

    public TomtomApiServiceImp(@Qualifier("tomtomRestClient") RestClient tomtomRestClient) {
        this.tomtomRestClient = tomtomRestClient;
    }

    public NearbySearchResponse findPlaces(String latitude,String longitude){
        return tomtomRestClient.get()
                .uri("/nearbySearch/.json?key=%s&lat=%s&lon=%s&categorySet=7376".formatted(apiKey,latitude,longitude))
                .retrieve()
                .body(NearbySearchResponse.class);
    }
}
