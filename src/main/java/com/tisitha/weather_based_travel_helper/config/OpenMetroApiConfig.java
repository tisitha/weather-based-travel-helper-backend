package com.tisitha.weather_based_travel_helper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class OpenMetroApiConfig {

    @Bean("openMetroRestClient")
    public RestClient openMetroRestClient(RestClient.Builder restClientBuilder){
        return restClientBuilder
                .baseUrl("https://api.open-meteo.com/v1")
                .build();
    }
}
