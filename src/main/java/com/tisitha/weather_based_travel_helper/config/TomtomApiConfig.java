package com.tisitha.weather_based_travel_helper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class TomtomApiConfig {

    @Bean("tomtomRestClient")
    public RestClient tomtomRestClient(RestClient.Builder restClientBuilder){
        return restClientBuilder
                .baseUrl("https://api.tomtom.com/search/2")
                .build();
    }
}
