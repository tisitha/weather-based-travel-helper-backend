package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.tomtomDtos.NearbySearchResponse;
import com.tisitha.weather_based_travel_helper.exception.ExternalApiException;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

@Service
public class TomtomApiServiceImp implements TomtomApiService{

    @Value("${tomtom.api.key}")
    private String apiKey;

    private final RestClient tomtomRestClient;

    public TomtomApiServiceImp(@Qualifier("tomtomRestClient") RestClient tomtomRestClient) {
        this.tomtomRestClient = tomtomRestClient;
    }

    @Cacheable(value = "nearbyPlaces", key = "#latitude+'_'+#longitude")
    @Retry(name = "findPlacesRetry")
    public NearbySearchResponse findPlaces(String latitude,String longitude){
        try{
            return tomtomRestClient.get()
                    .uri("/nearbySearch/.json?key=%s&lat=%s&lon=%s&categorySet=7376".formatted(apiKey,latitude,longitude))
                    .retrieve()
                    .body(NearbySearchResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new ExternalApiException(e.getMessage(),e.getStatusCode());
        } catch (ResourceAccessException e) {
            throw new ExternalApiException("this site can’t be reached "+e.getMessage(),HttpStatus.GATEWAY_TIMEOUT);
        } catch (RestClientException e) {
            throw new ExternalApiException("Unexpected error "+e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
}
