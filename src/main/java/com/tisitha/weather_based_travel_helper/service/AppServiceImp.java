package com.tisitha.weather_based_travel_helper.service;

import com.tisitha.weather_based_travel_helper.dto.AppResponseDto;
import com.tisitha.weather_based_travel_helper.dto.geoDtos.GeoLocationResponse;
import com.tisitha.weather_based_travel_helper.dto.openMetroDtos.WeatherDataResponse;
import com.tisitha.weather_based_travel_helper.dto.tomtomDtos.Poi;
import com.tisitha.weather_based_travel_helper.dto.tomtomDtos.Position;
import com.tisitha.weather_based_travel_helper.dto.tomtomDtos.Result;
import com.tisitha.weather_based_travel_helper.exception.InvalidDateException;
import com.tisitha.weather_based_travel_helper.exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppServiceImp implements AppService {

    private final GeoCodingApiService geoCodingApiService;
    private final TomtomApiService tomtomApiService;
    private final OpenMetroApiService openMetroApiService;

    public List<AppResponseDto> getData(String placeName,String date){

        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format. Use yyyy-mm-dd.");
        }

        LocalDate today = LocalDate.now();
        LocalDate maxDate = today.plusDays(15);

        if (parsedDate.isBefore(today) || parsedDate.isAfter(maxDate)) {
            throw new InvalidDateException("Date must be between today and the next 15 days.");
        }

        GeoLocationResponse geoLocationResponse = geoCodingApiService.searchPlace(placeName);

        if (geoLocationResponse.getResults()==null){
            throw new LocationNotFoundException("can not find a city by name");
        }

        String latitude = geoLocationResponse.getResults().getFirst().getLatitude();
        String longitude = geoLocationResponse.getResults().getFirst().getLongitude();

        List<Result> nearbyResults = tomtomApiService.findPlaces(latitude,longitude).getResults();
        List<AppResponseDto> appResponseDtoList = new ArrayList<>();

        if(nearbyResults.isEmpty()){
            throw new LocationNotFoundException("can not find a city by name");
        }

        for(Result nearbyResult:nearbyResults){

            AppResponseDto appResponseDto = new AppResponseDto();

            String lat = nearbyResult.getPosition().getLat();
            String lon = nearbyResult.getPosition().getLon();

            appResponseDto.setLatitude(lat);

            appResponseDto.setLongitude(lon);

            appResponseDto.setPlaceName(nearbyResult.getPoi().getName());

            WeatherDataResponse weatherDataResponse = openMetroApiService.getWeather(lat,lon,date);

            appResponseDto.setMaxTemp(String.valueOf(weatherDataResponse.getDaily().getTemperature_2m_max().getFirst()));

            appResponseDto.setPrecipitationSum(String.valueOf(weatherDataResponse.getDaily().getPrecipitation_sum().getFirst()));

            appResponseDto.setPrecipitationHour1(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(0)));
            appResponseDto.setPrecipitationHour2(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(1)));
            appResponseDto.setPrecipitationHour3(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(2)));
            appResponseDto.setPrecipitationHour4(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(3)));
            appResponseDto.setPrecipitationHour5(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(4)));
            appResponseDto.setPrecipitationHour6(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(5)));
            appResponseDto.setPrecipitationHour7(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(6)));
            appResponseDto.setPrecipitationHour8(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(7)));
            appResponseDto.setPrecipitationHour9(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(8)));
            appResponseDto.setPrecipitationHour10(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(9)));
            appResponseDto.setPrecipitationHour11(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(10)));
            appResponseDto.setPrecipitationHour12(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(11)));
            appResponseDto.setPrecipitationHour13(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(12)));
            appResponseDto.setPrecipitationHour14(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(13)));
            appResponseDto.setPrecipitationHour15(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(14)));
            appResponseDto.setPrecipitationHour16(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(15)));
            appResponseDto.setPrecipitationHour17(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(16)));
            appResponseDto.setPrecipitationHour18(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(17)));
            appResponseDto.setPrecipitationHour19(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(18)));
            appResponseDto.setPrecipitationHour20(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(19)));
            appResponseDto.setPrecipitationHour21(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(20)));
            appResponseDto.setPrecipitationHour22(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(21)));
            appResponseDto.setPrecipitationHour23(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(22)));
            appResponseDto.setPrecipitationHour24(String.valueOf(weatherDataResponse.getHourly().getPrecipitation().get(23)));

            appResponseDtoList.add(appResponseDto);

        }
        return appResponseDtoList;
    }

}
