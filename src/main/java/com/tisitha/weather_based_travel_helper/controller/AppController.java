package com.tisitha.weather_based_travel_helper.controller;

import com.tisitha.weather_based_travel_helper.dto.AppResponseDto;
import com.tisitha.weather_based_travel_helper.service.AppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/search")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/{placeName}/{date}")
    public ResponseEntity<List<AppResponseDto>> getData(@PathVariable String placeName, @PathVariable String date){
        return new ResponseEntity<>(appService.getData(placeName, date), HttpStatus.OK);
    }
}
