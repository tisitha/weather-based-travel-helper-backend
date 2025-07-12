package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.geoDtos.GeoLocationResponse;
import com.tisitha.weather_based_travel_helper.exception.ExternalApiException;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

@Service
public class GeoCodingApiServiceImp implements GeoCodingApiService{

    private final RestClient geoCodingRestClient;

    public GeoCodingApiServiceImp(@Qualifier("geoCodingRestClient") RestClient geoCodingRestClient) {
        this.geoCodingRestClient = geoCodingRestClient;
    }

    @Cacheable(value = "geoLocation", key = "#placeName")
    @Retry(name = "searchPlaceRetry")
    public GeoLocationResponse searchPlace(String placeName){
        try{
            return geoCodingRestClient.get()
                    .uri("/search?name=%s&count=1&language=en&format=json".formatted(placeName))
                    .retrieve()
                    .body(GeoLocationResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException(e.getMessage(),e.getStatusCode());
        } catch (ResourceAccessException e) {
            throw new ExternalApiException("this site can’t be reached "+e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
        } catch (RestClientException e) {
            throw new ExternalApiException("Unexpected error "+e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
}
