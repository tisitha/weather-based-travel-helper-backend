package com.tisitha.weather_based_travel_helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherBasedTravelHelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherBasedTravelHelperApplication.class, args);
	}

}
