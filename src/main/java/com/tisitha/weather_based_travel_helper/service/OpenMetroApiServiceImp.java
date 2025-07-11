package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.openMetroDtos.WeatherDataResponse;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OpenMetroApiServiceImp implements OpenMetroApiService{

    private final RestClient openMetroRestClient;

    public OpenMetroApiServiceImp(@Qualifier("openMetroRestClient") RestClient openMetroRestClient) {
        this.openMetroRestClient = openMetroRestClient;
    }

    @Retry(name = "getWeatherRetry")
    public WeatherDataResponse getWeather(String latitude, String longitude, String date){
        return openMetroRestClient.get()
                .uri("/forecast?latitude=%s&longitude=%s&daily=precipitation_sum,temperature_2m_max&hourly=precipitation&timezone=auto&start_date=%s&end_date=%s"
                        .formatted(latitude,longitude,date,date))
                .retrieve()
                .body(WeatherDataResponse.class);
    }
}
