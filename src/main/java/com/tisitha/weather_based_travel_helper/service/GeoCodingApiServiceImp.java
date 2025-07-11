package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.geoDtos.GeoLocationResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GeoCodingApiServiceImp implements GeoCodingApiService{

    private final RestClient geoCodingRestClient;

    public GeoCodingApiServiceImp(@Qualifier("geoCodingRestClient") RestClient geoCodingRestClient) {
        this.geoCodingRestClient = geoCodingRestClient;
    }

    @Retry(name = "searchPlaceRetry")
    public GeoLocationResponse searchPlace(String placeName){
        return geoCodingRestClient.get()
                .uri("/search?name=%s&count=1&language=en&format=json".formatted(placeName))
                .retrieve()
                .body(GeoLocationResponse.class);
    }
}
