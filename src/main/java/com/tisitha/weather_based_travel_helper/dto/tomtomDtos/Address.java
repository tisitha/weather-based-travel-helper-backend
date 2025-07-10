package com.tisitha.weather_based_travel_helper.dto.tomtomDtos;

import lombok.Data;

@Data
public class Address {

    private String streetName;
    private String municipalitySubdivision;
    private String municipality;
    private String countrySecondarySubdivision;
    private String countrySubdivision;
    private String postalCode;
    private String countryCode;
    private String country;
    private String countryCodeISO3;
    private String freeformAddress;
    private String localName;

}
