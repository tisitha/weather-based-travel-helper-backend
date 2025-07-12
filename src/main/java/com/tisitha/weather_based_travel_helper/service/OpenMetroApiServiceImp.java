package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.openMetroDtos.WeatherDataResponse;
import com.tisitha.weather_based_travel_helper.exception.ExternalApiException;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

@Service
public class OpenMetroApiServiceImp implements OpenMetroApiService{

    private final RestClient openMetroRestClient;

    public OpenMetroApiServiceImp(@Qualifier("openMetroRestClient") RestClient openMetroRestClient) {
        this.openMetroRestClient = openMetroRestClient;
    }

    @Cacheable(value = "weather", key = "#latitude+'_'+#longitude+'_'+#date")
    @Retry(name = "getWeatherRetry")
    public WeatherDataResponse getWeather(String latitude, String longitude, String date){
        try{
            return openMetroRestClient.get()
                    .uri("/forecast?latitude=%s&longitude=%s&daily=precipitation_sum,temperature_2m_max&hourly=precipitation&timezone=auto&start_date=%s&end_date=%s"
                            .formatted(latitude,longitude,date,date))
                    .retrieve()
                    .body(WeatherDataResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException(e.getMessage(),e.getStatusCode());
        } catch (ResourceAccessException e) {
            throw new ExternalApiException("this site can’t be reached "+e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
        } catch (RestClientException e) {
            throw new ExternalApiException("Unexpected error "+e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
}
